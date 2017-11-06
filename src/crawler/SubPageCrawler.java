package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.Dataset;

public class SubPageCrawler {

	private Document document;

	public SubPageCrawler(String url) {
		System.out.println("Connecting the sub-website ...");
		try {
			this.document = Jsoup.connect(url).userAgent("Mozilla").maxBodySize(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");
	}
	
	public List<Dataset> getDatasetDetails(List<Dataset> DatasetTitles) {
		System.out.println("Retrieving the sub-website data.");
		List<Dataset> dataset = new ArrayList<Dataset>();
		Elements elements = document.select("tbody");
		
		for (Dataset datasetTitle : DatasetTitles) {
			Dataset detail = new Dataset();
			detail.setId(datasetTitle.getId());
			detail.setItem(datasetTitle.getItem());
			Element linkElement = elements.select("tr td a:contains(" + datasetTitle.getItem() + ")").first();
			Element tdElement = linkElement.parent().parent().select("td.score").first();
			detail.setScore(Integer.parseInt(tdElement.attr("data-score")));
			dataset.add(detail);
		}

		System.out.println("Done.");
		return dataset;
	}
	
}
