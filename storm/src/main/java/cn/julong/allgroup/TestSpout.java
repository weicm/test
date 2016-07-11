package cn.julong.allgroup;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 * Created by Think on 2016/6/8.
 */
public class TestSpout extends BaseRichSpout {
	private SpoutOutputCollector collector = null;
	private Random random = null;
	private int count = 0;

	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		random = new Random();
	}

	public void nextTuple() {
		if(count >= 10)
			return;
		collector.emit(new Values(random.nextInt(4)));
		count++;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
	}
}
