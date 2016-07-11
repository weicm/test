package cn.julong.blockqueue;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.List;
import java.util.Map;

/**
 * Created by Think on 2016/6/8.
 */
public class TestSpout extends BaseRichSpout {
	private SpoutOutputCollector collector = null;
	private int count = 0;

	private List<Integer> bolts = null;
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	public void nextTuple() {
		//只用来控制发射tuple次数，没有别的作用
		if(count >= 10)
			return;
		collector.emit(new Values(""));
		count++;

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
	}
}
