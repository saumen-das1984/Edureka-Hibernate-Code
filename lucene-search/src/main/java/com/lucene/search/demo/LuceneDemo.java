package com.lucene.search.demo;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.ByteBuffersDirectory;

public class LuceneDemo {
	private static void addDocument(IndexWriter indexWriter, String title, String key) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));
		doc.add(new StringField("key", key, Field.Store.YES));
		indexWriter.addDocument(doc);
	}

	private static void populateIndexWriter(Directory index, IndexWriterConfig config) throws IOException {
		IndexWriter indexWriter = new IndexWriter(index, config);
		addDocument(indexWriter, "Edureka provides online training courses", "123");
		addDocument(indexWriter, "Edureka makes learning easy and affordable", "456");
		addDocument(indexWriter, "Learning new technologies online makes life easier", "789");
		addDocument(indexWriter, "Edureka has highly qualified faculties", "156");
		addDocument(indexWriter, "Learing online provides wide options for the learner", "189");
		indexWriter.close();
	}

	public static void main(String[] args) {
		try {

			StandardAnalyzer analyzer = new StandardAnalyzer();
			Directory index = new ByteBuffersDirectory();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			populateIndexWriter(index, config);
			String querystr = "Edureka";
			Query q = new QueryParser("title", analyzer).parse(querystr);
			int hitsPerPage = 10;
			IndexReader reader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, Integer.MAX_VALUE);
			searcher.search(q, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.out.println("Total " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("key") + "	" + d.get("title"));
			}

			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
