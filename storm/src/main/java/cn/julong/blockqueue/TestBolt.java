package cn.julong.blockqueue;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

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
		//偶数task发送0，基数task发送1
		if(context.getThisTaskId()%2 == 0) {
			collector.emit(new Values(0));
		}else if(context.getThisTaskId()%2 == 1) {
			collector.emit(new Values(1));
		}
		collector.ack(input);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
	}
}
