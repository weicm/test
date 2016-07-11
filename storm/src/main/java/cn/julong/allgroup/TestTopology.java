package cn.julong.allgroup;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * Created by Think on 2016/6/8.
 */
public class TestTopology {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("TestSpout", new TestSpout(), 1);
		builder.setBolt("TestBolt", new TestBolt(), 2).allGrouping("TestSpout");

		Config config = new Config();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("TestTopology", config, builder.createTopology());
	}
}
