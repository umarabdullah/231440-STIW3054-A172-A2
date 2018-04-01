/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054_assignment.quiz1xcel;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import java.util.Map;

/**
 *
 * @author abdullah
 */
public class SentenceSpout extends BaseRichSpout {
	private SpoutOutputCollector collector;
	private String sentences = WordCountTopology.sent;
	private int index = 0;
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("sentence"));
	}
	public void open(Map config, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
	}
	public void nextTuple() {
		collector.emit(new Values(sentences));
		
	
	}
}
