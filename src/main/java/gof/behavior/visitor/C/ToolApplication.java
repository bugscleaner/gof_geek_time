package gof.behavior.visitor.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 **/
public class ToolApplication {
    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }

        Compressor compressor = new Compressor();
        for(ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(compressor);
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirecto){
        List<ResourceFile> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        return resourceFiles;
    }


}
