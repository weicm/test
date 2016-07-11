package cn.julong.allgroup;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by Think on 2016/6/8.
 */
public class TestBolt extends BaseRichBolt {
	private OutputCollector collector = null;
	private TopologyContext context = null;


	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.context = context;
		this.collector = collector;
	}

	public void execute(Tuple input) {
		System.out.println("taskID: " + context.getThisTaskId() + "-----------------------value: " + input.getInteger(0));
		collector.ack(input);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
	}
}
