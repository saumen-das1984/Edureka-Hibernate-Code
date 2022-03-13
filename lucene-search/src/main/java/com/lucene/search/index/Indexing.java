package com.lucene.search.index;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.lucene.search.util.Filter;

public class Indexing {
	private Document getDocument(File file) throws IOException {
		Document document = new Document();
		Field content = new TextField("content", new FileReader(file));
		Field fileName = new TextField("name", new FileReader(file));
		Field filePath = new TextField("path", new FileReader(file));
		document.add(content);
		document.add(fileName);
		document.add(filePath);
		return document;
	}

	private int createIndex(String dataDirPath, Filter filter,IndexWriter writer)
			throws IOException {
		File[] files = new File(dataDirPath).listFiles();
		for (File file : files) {
			if (!file.isDirectory() && !file.isHidden() && file.exists()
					&& file.canRead() && filter.accept(file)) {
				 Document document = getDocument(file);
			     writer.addDocument(document);
			}
		}
		return writer.numRamDocs();
	}

	public static void main(String args[]) throws IOException {

		Indexing indexing=new Indexing();
		String indexDirectoryPath = "C:\\Edureka Projects\\index";
		Path path = Paths.get(indexDirectoryPath);
		String dataPath = "C:\\Edureka Projects\\data";
		Directory indexDirectory = FSDirectory.open(path);
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(indexDirectory, config);
		int indexedTotal=indexing.createIndex(dataPath, new Filter(), writer);
		writer.close();
		System.out.println("Total files indexed " + indexedTotal);
	}
}
