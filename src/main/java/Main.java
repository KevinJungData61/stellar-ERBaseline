import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import data.Record;
import er.rest.api.RestParameters;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.ERProfile;
import er.rest.RestServer;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import er.ER;

public class Main {
    static RestServer restServer;
    static ER er;

    //main function reads in a property file which specifies FileSource, MatcherMerger,
    // and Algorithm(probably next step)
    public static void main(String[] args) throws Exception  {

        ERProfile erProfile = ERProfile.build();
        Parameters para = new Parameters();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(para)
                .build();
        jCommander.parse(args);
        jCommander.setProgramName(erProfile.getName(), erProfile.getVersion());

        if (para.help) {
            jCommander.usage();
            return;
        } else if (para.rest) {
            restServer = new RestServer();
            restServer.start();
        } else if (para.cli != null) {
            ER er = new ER();
            Set<Record> records = er.parseConfigFile(para.cli);
            Set<Record> results = er.runRSwoosh(records);
            System.out.println("Output results: " + results.size());

            er.writeResults(results);
        } else if (para.url != null) {
            HttpPost httpPost = new HttpPost(URI.create(para.url));

            RestParameters rp = new RestParameters();
            rp.prefix = "datasets/ACM_DBLP";
            rp.dataformat = "epgm";
            rp.fileSources = "er_acm_dblp2.epgm";
            rp.outputFile = "output.epgm";

            Map<String, Double> am = new HashMap<>();
            am.put("venue", 0.5);
            am.put("author", 0.7);
            am.put("title", 0.9);
            rp.attributes = am;

            Gson gson = new Gson();
            StringEntity params = new StringEntity(gson.toJson(rp));
            httpPost.setEntity(params);
            httpPost.setHeader("Content-type", "application/json");

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
        } else {
            jCommander.usage();
            return;
        }
    }
}