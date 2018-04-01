/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054_assignment.quiz1xcel;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author abdullah
 */
public class WordCountTopology {
        
	private static final String SENTENCE_SPOUT_ID = "sentence-spout";
	private static final String SPLIT_BOLT_ID = "split-bolt";
	private static final String COUNT_BOLT_ID = "count-bolt";
	private static final String REPORT_BOLT_ID = "report-bolt";
	private static final String TOPOLOGY_NAME = "word-count-topology";
        public static String sent;
	public static void main(String[] args) throws Exception {
        long starting,stoppingbpf,timeusedbpf,stoppingapf,timeusedapf;
        starting = System.nanoTime();
        ReadExcelFile names = new ReadExcelFile (); 
        StudentList studnames = new StudentList();
        studnames = names.createList();
        //System.out.println("HAPPY");
        //printNames(studnames);
        WriteToMdFile write = new WriteToMdFile (studnames);
        write.WriteFile();
        File f = new File("/Users/abdullah/Downloads/Report.markdown");
        FileWriter file = new FileWriter (f);
        BufferedWriter bwrit = new BufferedWriter(file);
        String text = "## Assignment Report\n----\n" +
                   "### Number of Words : " + SplitSentenceBolt.words.length +
                    "----\n" +
                   "### Number of Characters : " + SplitWordBolt.words.length +
                    "----\n";
           String append = text;
           bwrit.write(append);
           bwrit.close();
       
        
        sent = write.getArticle();
		SentenceSpout spout = new SentenceSpout();
		SplitSentenceBolt splitBolt = new SplitSentenceBolt();
                SplitWordBolt splitwBolt = new SplitWordBolt();
		WordCountBolt countBolt = new WordCountBolt();
		ReportBolt reportBolt = new ReportBolt();
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout(SENTENCE_SPOUT_ID, spout);
		// SentenceSpout --> SplitSentenceBolt
		builder.setBolt(SPLIT_BOLT_ID, splitBolt)
		.shuffleGrouping(SENTENCE_SPOUT_ID);
                builder.setBolt("Split-word", splitwBolt)
		.shuffleGrouping(SENTENCE_SPOUT_ID);

		// SplitSentenceBolt --> WordCountBolt
		builder.setBolt(COUNT_BOLT_ID, countBolt)
		.fieldsGrouping(SPLIT_BOLT_ID, new Fields("word"));
		// WordCountBolt --> ReportBolt
		builder.setBolt(REPORT_BOLT_ID, reportBolt)
		.globalGrouping(COUNT_BOLT_ID);
		Config config = new Config();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(TOPOLOGY_NAME, config, builder.
				createTopology());
	        //waitForSeconds(10);
		cluster.killTopology(TOPOLOGY_NAME);
		cluster.shutdown();
                stoppingbpf = System.nanoTime();
                timeusedbpf = stoppingbpf-starting; 
        String cmd = "cd 231440-STIW3054-A172-A1.wiki";
        String command = "git add /Users/abdullah/Downloads/Report.markdown";
        String cmd2 = "git push";

        Process proc1 = Runtime.getRuntime().exec(cmd);
        Process proc2 = Runtime.getRuntime().exec(command);
        Process proc3 = Runtime.getRuntime().exec(cmd2);
        System.out.println("Waiting for execution");
        proc1.waitFor();
        proc2.waitFor();
        proc3.waitFor();
        stoppingapf = System.nanoTime();
        timeusedapf = stoppingapf - stoppingbpf;
        double time;
        time = timeusedbpf / 1000000000.0;
            System.out.println("Time used Before Uploading File :" + time + "seconds\n");
            time = timeusedapf / 1000000000.0;
            System.out.println("Time used After Uploading File :" + time + "seconds");
	}
}
