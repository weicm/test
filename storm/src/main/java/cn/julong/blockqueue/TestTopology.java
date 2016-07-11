package cn.julong.blockqueue;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * spout任务只有一个，发送10个空白字符tuple出去
 * bolt任务有两个，共同消费上面10个空白字符tuple，并切偶数任务发射值为0的tuple，基数线程发射值为1的tuple
 * bolt2任务有3个，从bolt接受的值只有0和1两种，所以只会有两个任务接受到特定的值并打印，bolt并以fieldgroup的方式分组到bolt2的3个任务的
 * bolt3任务有2个，也从bolt或的10个0和1的tuple，bolt并以shuffle方式分组到bolt3的两个任务的
 *
 * 这个例子说明storm的group策略是针对组件（具体的spout/bolt类）的task的，而不是针对组件的
 *
 * Created by Think on 2016/6/8.
 */
public class TestTopology {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("TestSpout", new TestSpout(), 1);
		builder.setBolt("TestBolt", new TestBolt(), 2).shuffleGrouping("TestSpout");
		builder.setBolt("TestBolt2", new TestBolt2(), 3).fieldsGrouping("TestBolt", new Fields("value"));
		builder.setBolt("TestBolt3", new TestBolt3(), 2).shuffleGrouping("TestBolt");

		Config config = new Config();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("TestTopology", config, builder.createTopology());
	}
}
