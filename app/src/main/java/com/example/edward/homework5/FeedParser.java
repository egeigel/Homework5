package com.example.edward.homework5;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by edward on 10/23/17.
 */

public class FeedParser {
    public static class FeedSaxParser extends DefaultHandler{

        ArrayList<Entry> entries;
        Entry entry;
        StringBuilder innerXml;
        int height;
        int smallestHeight;
        int largestHeight;

        public static ArrayList<Entry> parseFeed(InputStream inputStream) throws IOException, SAXException {
            // stream of XML is pushed to program
            FeedSaxParser feedSaxParser = new FeedSaxParser();
            Xml.parse(inputStream , Xml.Encoding.UTF_8 , feedSaxParser);
            return feedSaxParser.entries;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            // when doc starts, initiate new list to store data
            entries = new ArrayList<>();
            innerXml = new StringBuilder();
            height = 0;
            smallestHeight = 0;
            largestHeight = 0;
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("entry")){
                // prepare to store entry item
                entry = new Entry();
            }

            if(localName.equals("image")) {
                if (entry != null) {
                    // prepare to store image
                    height = Integer.valueOf(attributes.getValue("height"));
                    Log.d("demo", String.valueOf(height));
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("entry")){
                // closing tag
                // add entry to array list
                entries.add(entry);
                largestHeight = 0;
                smallestHeight = 0;
            }
            else if(localName.equals("title")){
                if(entry!=null){
                    entry.setTitle(innerXml.toString());
                }
            }
            else if(localName.equals("summary")) {
                if (entry != null) {
                    entry.setSummary(innerXml.toString());

                }
            }
            else if(localName.equals("releaseDate")) {
                if (entry != null) {
                    entry.setReleaseDate(innerXml.toString());

                }
            }
            else if(localName.equals("image")) {
                if(entry!=null) {
                    if(entry.getSmallImage()==null){
                        // if there is no image in the entry, store
                        entry.setSmallImage(innerXml.toString());
                        // set to pre-stored height
                        smallestHeight = height;
                        largestHeight = height;
                    }
                    else if (height < smallestHeight) {
                        entry.setSmallImage(innerXml.toString());
                        Log.d("demo" , String.valueOf(height) + " is the smallest");
                    }
                    else if (height > largestHeight) {
                        entry.setLargeImage(innerXml.toString());
                        Log.d("demo" , String.valueOf(height) + " is the largest");
                    }
                }
            }
            innerXml.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            innerXml.append(ch , start, length);
        }
    }
}
