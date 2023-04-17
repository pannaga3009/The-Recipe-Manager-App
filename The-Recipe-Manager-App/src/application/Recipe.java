package application;

import javafx.scene.image.Image;

public class Recipe {

	    private String name;
	    private String chefName;
	    private String ImgSrc;
		private double rating;
	

		private String description;
		private String ingredients;
		private Image image;


	   
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getchefName() {
	        return chefName;
	    }

	    public void setchefName(String chefName) {
	        this.chefName = chefName;
	    }

	    public String getImage() {
	        return ImgSrc;
	    }

	    public void setImage(String ImgSrc) {
	    	System.out.println("----img----"+ImgSrc);
	        this.ImgSrc = ImgSrc;
	    }
	    
	    public double getRating() {
	        return rating;
	    }

	    public void setRating(double rating) {
	        this.rating = rating;
	    }


	    public void setDescription(String description) {
	    	 this.description = description;
	    }
		public String getDescription() {
			
			return description;
		}
		
		public void setContents(String ingredients) {
			this.ingredients = ingredients;
		}
		
		public String getContents() {
			return ingredients;
		}
		
		public void setImageDetail(Image img) {
			this.image = img;
		}
		
		public Image getImageDetail(Image img) {
			return img;
		}
		
		 
	   
	}

