
public class Tag {
      private int qid;      
	  private String Tag;
       
      public Tag() {
    	  
      };
      
      public Tag(String Tag) {
    	  this.Tag=Tag;
      };
      
      public Tag(int qid, String Tag) {
    	  this.qid=qid;
    	  this.Tag=Tag;
      }
      
      public int getQid() {
  		return qid;
  	}

  	public void setQid(int qid) {
  		this.qid = qid;
  	}

  	public String getTag() {
  		return Tag;
  	}

  	public void setTag(String tag) {
  		Tag = tag;
  	}

}
