package com.bbn.parliament.jena.graph.index.spatial.geosparql.function;

import com.hp.hpl.jena.graph.Node;

public class UnsupportedUnitsException extends GeoSPARQLFunctionException {
	private static final long serialVersionUID = 1L;

	public UnsupportedUnitsException(Node units) {
		super(String.format("%s is not supported for this function", units));
	}
}
