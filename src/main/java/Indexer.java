import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Indexer {
    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException{
    }

    public static void main(String[] args){
        try{
            //建立索引的目录
            final Path path = Paths.get("./article/");

            //此类表示索引的存储位置
            Directory directory = FSDirectory.open(path);

            //Analyzer类负责分析一个文件，并从将被索引的文本获取令牌/字。
            Analyzer analyzer = new StandardAnalyzer();

            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

            //设置模式CREATE
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            //IndexWriter用于更新或创建索引
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);


            //Field是最低单元或索引过程的起点。它代表其中一个键被用于识别要被索引的值的键值对关系。用于表示一个文件内容的字段将具有键为“内容”，值可以包含文本或文档的数字内容的部分或全部。 Lucene能索引仅文本或仅数字内容。

                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/jiangxl/GitDev/LucenePractice/src/main/resources/j.txt")));
                String content = "";
                //StringBuilder sb = new StringBuilder();
                while ((content = bufferedReader.readLine())!=null){
                    System.out.println(content);
                    Document document = new Document();
                    document.add(new TextField("text", content, Field.Store.YES));
                    indexWriter.addDocument(document);
                }
            indexWriter.close();
        }catch(Exception e){e.printStackTrace();}
    }
}
