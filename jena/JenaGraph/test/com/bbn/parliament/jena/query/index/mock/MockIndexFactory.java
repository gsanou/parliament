package com.bbn.parliament.jena.query.index.mock;

import java.util.Properties;

import com.bbn.parliament.jena.graph.index.IndexFactory;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;

public class MockIndexFactory extends IndexFactory<MockIndex, Integer> {
	public MockIndexFactory() {
		super("Mock");
	}

	@Override
	public void configure(Properties configuration) {
	}

	@Override
	public MockIndex createIndex(Graph graph, Node graphName, String indexDir) {
		return new MockIndex();
	}
}
