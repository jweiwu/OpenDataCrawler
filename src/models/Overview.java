package models;

import java.util.List;

public class Overview {
	private int id;
	private int rank;
	private int score;
	private String place;
	private String url;
	private List<Dataset> Datasets;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Dataset> getDatasets() {
		return Datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		Datasets = datasets;
	}

}
