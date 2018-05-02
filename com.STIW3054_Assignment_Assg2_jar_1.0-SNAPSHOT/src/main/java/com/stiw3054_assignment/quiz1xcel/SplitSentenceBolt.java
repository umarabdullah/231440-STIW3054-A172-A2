/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054_assignment.quiz1xcel;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abdullah
 */

    public class SplitSentenceBolt extends BaseRichBolt{
	private OutputCollector collector;
        public static String [] words;
        public static String  sentence;
	public void prepare(Map config, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}
	public void execute(Tuple tuple) {
	        sentence = tuple.getStringByField("sentence");
		String[] wors = sentence.split(" ");
                sentence="";
                for(String w : wors){
                  sentence += w;
                }
                words = sentence.split(";");
		for(String word : words){
			this.collector.emit(new Values(word));
		}
	}
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

    public  String[] getWords() {
        return words;
    }
        
}

