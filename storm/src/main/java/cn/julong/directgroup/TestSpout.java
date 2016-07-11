package cn.julong.directgroup;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Think on 2016/6/8.
 */
public class TestSpout extends BaseRichSpout {
	private SpoutOutputCollector collector = null;
	private Random random = null;
	private int count = 0;

	private List<Integer> bolts = null;
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		random = new Random();
		bolts = context.getComponentTasks("TestBolt");
	}

	public void nextTuple() {
		if(count >= 10)
			return;
		int value = random.nextInt(4);
		if(value%2 == 0) {
			collector.emitDirect(bolts.get(0), new Values(value));
		}else {
			collector.emitDirect(bolts.get(1), new Values(value));
		}
		count++;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
	}
}
