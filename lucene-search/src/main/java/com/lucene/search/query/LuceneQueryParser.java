package com.lucene.search.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

public class LuceneQueryParser {
	public Query buildQuery(String searchPattern, String[] fields) throws ParseException {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Map<String, Analyzer> analyzerPerField = new HashMap<String, Analyzer>();
		analyzerPerField.put("acc", new KeywordAnalyzer());
		analyzerPerField.put("investigation_acc", new KeywordAnalyzer());
		PerFieldAnalyzerWrapper aWrapper = new PerFieldAnalyzerWrapper(analyzer, analyzerPerField);
		QueryParser parser = new MultiFieldQueryParser(fields, aWrapper);
		parser.setAllowLeadingWildcard(true);
		//parser.set
		Query luceneQuery = parser.parse(searchPattern);
		System.out.println("luceneQuery = " + luceneQuery);
		return luceneQuery;
	}

	public static void main(String args[]) throws ParseException {
		String searchPattern = "java";
		String fields[] = { "java", ".net", "oracle", "bidata" };
		new LuceneQueryParser().buildQuery(searchPattern, fields);
	}
}
