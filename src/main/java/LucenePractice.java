import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;



//根据所索引进行查询

public class LucenePractice{
    public static void main(String[] args){

        try{
            String query = "流量";

            final Path path = Paths.get("./article/");

            Directory directory = FSDirectory.open(path);

            Analyzer analyzer = new StandardAnalyzer();
            IndexReader indexReader = DirectoryReader.open(directory);

            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            QueryParser queryParser = new QueryParser("text",analyzer);
            Query q = queryParser.parse(query);

            TopDocs topDocs = indexSearcher.search(q,10);

            long count = topDocs.totalHits;

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for(ScoreDoc scoreDoc:scoreDocs){
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(scoreDoc.score);
                System.out.println(document.get("text"));
            }
        }catch(Exception e){e.printStackTrace();}
    }
}