package com.bbn.parliament.jena.query.index.pfunction.algebra;

import org.openjena.atlas.io.IndentedWriter;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.sparql.algebra.Op;
import com.hp.hpl.jena.sparql.algebra.op.OpExt;
import com.hp.hpl.jena.sparql.algebra.op.OpPropFunc;
import com.hp.hpl.jena.sparql.core.BasicPattern;
import com.hp.hpl.jena.sparql.engine.ExecutionContext;
import com.hp.hpl.jena.sparql.engine.QueryIterator;
import com.hp.hpl.jena.sparql.pfunction.PropFuncArg;
import com.hp.hpl.jena.sparql.procedure.Procedure;
import com.hp.hpl.jena.sparql.serializer.SerializationContext;
import com.hp.hpl.jena.sparql.util.NodeIsomorphismMap;

/** @author rbattle */
public class OpIndexPropFunc extends OpExt {
	private Op subOp;
	private BasicPattern pattern;
	private Node uri;
	private PropFuncArg subjectArgs;
	private PropFuncArg objectArgs;

	public OpIndexPropFunc(Node uri, PropFuncArg subjectArgs,
		PropFuncArg objectArgs, BasicPattern pattern, Op subOp) {
		super("indexfunc");
		this.uri = uri;
		this.subjectArgs = subjectArgs;
		this.objectArgs = objectArgs;
		this.subOp = subOp;
		this.pattern = pattern;
	}

	public BasicPattern getPattern() {
		return pattern;
	}

	public Op getSubOp() {
		return subOp;
	}

	@Override
	public Op effectiveOp() {
		return new OpPropFunc(uri, subjectArgs, objectArgs, subOp);
	}

	@Override
	public void outputArgs(IndentedWriter out, SerializationContext sCxt) {
		out.ensureStartOfLine();
		for (Triple t : pattern) {
			out.println(t.toString());
		}

		subjectArgs.output(out, sCxt);
		out.print(' ');
		out.print(uri);
		out.print(' ');
		objectArgs.output(out, sCxt);

		// output sub operation
		out.ensureStartOfLine();
		subOp.output(out, sCxt);
	}

	@Override
	public QueryIterator eval(QueryIterator input, ExecutionContext execCxt) {
		// Node predicate = this.propFunc.getProperty();
		// String uri = predicate.getURI();
		// PropFuncArg argSubject = this.propFunc.getSubjectArgs();
		// PropFuncArg argObject = this.propFunc.getObjectArgs();
		Procedure proc = IndexProcBuilder.build(uri, subjectArgs, objectArgs,
			pattern, execCxt);
		return proc.proc(input, execCxt);
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((objectArgs == null) ? 0 : objectArgs.hashCode());
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		result = prime * result + ((subOp == null) ? 0 : subOp.hashCode());
		result = prime * result
			+ ((subjectArgs == null) ? 0 : subjectArgs.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equalTo(Op obj, NodeIsomorphismMap labelMap) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		OpIndexPropFunc other = (OpIndexPropFunc) obj;
		if (objectArgs == null) {
			if (other.objectArgs != null)
				return false;
		} else if (!objectArgs.equals(other.objectArgs))
			return false;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		if (subOp == null) {
			if (other.subOp != null)
				return false;
		} else if (!subOp.equalTo(other.subOp, labelMap))
			return false;
		if (subjectArgs == null) {
			if (other.subjectArgs != null)
				return false;
		} else if (!subjectArgs.equals(other.subjectArgs))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
}
