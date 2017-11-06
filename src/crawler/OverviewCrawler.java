package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.Dataset;
import models.Overview;

public class OverviewCrawler {
	
	protected Document document;
	
	public OverviewCrawler(String url){
		System.out.println("Connecting the website ...");
		try {
			this.document = Jsoup.connect(url).userAgent("Mozilla").maxBodySize(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");
	}
	
	public List<Dataset> getDatasets(){
		System.out.println("Retrieving the dataset titles.");
		List<Dataset> datasets = new ArrayList<Dataset>();
		Elements elements = document.select("thead tr th div a");
		
		for (int i = 0; i < elements.size(); i++) {
			Dataset dataset = new Dataset();
			dataset.setId(i);
			dataset.setItem(elements.get(i).text());
			datasets.add(dataset);
		}
		System.out.println("Done.");
		return datasets;
	}
	
	public List<Overview> getRows(){
		System.out.println("Retrieving the overview data.");
		List<Overview> rows = new ArrayList<Overview>();
		Elements elements = document.select("tbody tr");
		
		for (int i = 0; i < elements.size(); i++) {
			Element trElement = elements.get(i);
			Elements trChildren = trElement.children();
			
			Overview overview = new Overview();
			overview.setId(i);
			overview.setRank(Integer.parseInt(trElement.attr("data-rank")));
			overview.setScore(Integer.parseInt(trElement.attr("data-score")));
			overview.setPlace(trChildren.get(1).text());
			overview.setUrl(trChildren.get(1).child(0).attr("href"));
			
			rows.add(overview);
		}

		System.out.println("Done.");
		return rows;
	}

}
