package beans;

import java.time.LocalDateTime;

public class Item {
	private int itemId;
	private String name;
	private String description;
	private String auctionType;
	private double price;
	private double highestBid;
	private String bidder;
	private LocalDateTime auctionEndTime;
	private double shippingTime;
	private double shippingPrice;
	private double expeditedCost;
	
	public Item() {
		
	}
	
	public Item(int itemId, String name, String description, String auctionType, double price, double highestBid,
			String bidder, LocalDateTime auctionEndTime, double shippingTime, double shippingPrice,
			double expeditedCost) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.auctionType = auctionType;
		this.price = price;
		this.highestBid = highestBid;
		this.bidder = bidder;
		this.auctionEndTime = auctionEndTime;
		this.shippingTime = shippingTime;
		this.shippingPrice = shippingPrice;
		this.expeditedCost = expeditedCost;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getHighestBid() {
		return highestBid;
	}
	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}
	public String getBidder() {
		return bidder;
	}
	public void setBidder(String bidder) {
		this.bidder = bidder;
	}
	public LocalDateTime getAuctionEndTime() {
		return auctionEndTime;
	}
	public void setAuctionEndTime(LocalDateTime auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}
	public double getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(double shippingTime) {
		this.shippingTime = shippingTime;
	}
	public double getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	public double getExpeditedCost() {
		return expeditedCost;
	}
	public void setExpeditedCost(double expeditedCost) {
		this.expeditedCost = expeditedCost;
	}
	
}
