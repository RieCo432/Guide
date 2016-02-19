package com.colinries.guide;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class FaqParser {
    //TODO: remove all log.i calls and other debugging stuff

    private static final String ns = null;

    public List<Entry> parse(InputStream in) throws XmlPullParserException, IOException {
        Log.i("FAQP", "parse() called");
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in,null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Entry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.i("FAQP", "readFeed() called");
        List<Entry> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            Log.i("FAQP", parser.getName());
            if(name.equals("entry")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }

        }
        return entries;
    }

    public static class Entry {
        public final String question;
        public final String answer;

        private Entry(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }

    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.i("FAQP", "readEntry() called");
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String question = null;
        String answer = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            Log.i("FAQP", "switch q/a" + parser.getName());
            switch (name) {
                case "question":
                    question = readQuestion(parser);
                    break;
                case "answer":
                    answer = readAnswer(parser);
                    break;
                default:
                    Log.i("FAQP", "default: skip() called");
                    skip(parser);
                    break;
            }
        }
        return new Entry(question, answer);
    }

    private String readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        Log.i("FAQP", "readQuestion() called");
        parser.require(XmlPullParser.START_TAG, ns, "question");
        String question = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "question");
        return question;
    }

    private String readAnswer(XmlPullParser parser) throws IOException, XmlPullParserException {
        Log.i("FAQP", "readAnswer() called");
        parser.require(XmlPullParser.START_TAG, ns, "answer");
        String answer = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answer");
        return answer;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        Log.i("FAQP", "readText() called");
        String result = "";
        if (parser.next() ==XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        } else {
            result = "N/A";
        }

        Log.i("FAQP", result);
        //Toast.makeText(new FAQFragment().getActivity(), result, Toast.LENGTH_SHORT).show();
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.i("FAQP", "skip() called");
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
