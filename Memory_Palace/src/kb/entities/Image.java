package kb.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Image
 *
 */
@Entity

public class Image implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="img_id")
	private int img_id;
	
	@Column(name="img_data")
	private File img_data;
	
	@Column(name="img_md5")
	private Byte img_md5;
	
	public int getImg_id() {
		return img_id;
	}

	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}

	public File getImg_data() {
		return img_data;
	}

	public void setImg_data(File img_data) {
		this.img_data = img_data;
	}

	public Byte getImg_md5() {
		return img_md5;
	}

	public void setImg_md5(Byte img_md5) {
		this.img_md5 = img_md5;
	}

	public Image() {
		super();
	}
   
}
