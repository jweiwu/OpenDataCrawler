package crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import models.Dataset;
import models.Overview;

public class Main {

	public static void main(String[] args) {
		
		String url = "https://index.okfn.org/place/";
		String savePath = "D:\\getData\\data.csv";
		OverviewCrawler overviewCrawler = new OverviewCrawler(url);
		
		List<Dataset> datasets = overviewCrawler.getDatasets();
		List<Overview> overview = overviewCrawler.getRows();
		
		for (Overview subPage : overview) {
			String subUrl = subPage.getUrl();
			SubPageCrawler subPageCrawler = new SubPageCrawler(url + subUrl.substring(subUrl.lastIndexOf("/") + 1));
			List<Dataset> datasetDetail = subPageCrawler.getDatasetDetails(datasets);
			subPage.setDatasets(datasetDetail);
			
		}
		
		Writing(savePath, overview, datasets);
		
	}
	
	public static void Writing(String savePath, List<Overview> data, List<Dataset> dataSetTitles) {
		System.out.println("Writing the data.");
		try {
			
			FileWriter fw = new FileWriter(savePath);
			String saveStr = "\"Rank\",\"Place\",";
			
			for (Dataset title : dataSetTitles) {
				saveStr += "\"" + title.getItem() + "\",";
			}
			
			saveStr += "\"Score\"\r\n";
			
			for (Overview row : data) {
				saveStr += "\"" + row.getRank() + "\"";
				saveStr += ",\"" + row.getPlace() + "\"";
				for (Dataset dataset : row.getDatasets()) {
					saveStr += ",\"" + dataset.getScore() + "\"";
				}
				saveStr += ",\"" + row.getScore() + "\"";
				saveStr += "\r\n";
			}
			
			fw.write(saveStr);
			fw.close();

			System.out.println("Done.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
