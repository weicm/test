package cn.julong.blockqueue;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Think on 2016/6/8.
 */
public class TestBolt2 extends BaseRichBolt {
	private OutputCollector collector = null;
	private TopologyContext context = null;
	private static ArrayList<String> list = new ArrayList<String>();
	private static int prepareCount = 0;


	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.context = context;
		this.collector = collector;
	}

	public void execute(Tuple input) {
		Integer value = input.getInteger(0);
		System.out.println("taskID: " + context.getThisTaskId() + "-------------bolt2----------value: " + value);
		collector.ack(input);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

}
