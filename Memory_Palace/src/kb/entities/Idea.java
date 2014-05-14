package kb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Idee
 *
 */
@Entity

public class Idea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idea_id")
	private int idea_id;
	
	@Column(name="idea_title")
	private String idea_title;
	
	@Column(name="idea_text")
	private String idea_text;
	
	@ManyToOne
	@JoinColumn(name="fk_img")
	private Image fk_img;
	
	public String getIdea_title() {
		return idea_title;
	}

	public void setIdea_title(String idea_title) {
		this.idea_title = idea_title;
	}

	public int getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}

	public String getIdeaText() {
		return idea_text;
	}

	public void setIdeaText(String idea_text) {
		this.idea_text = idea_text;
	}

	@Override
	public String toString() {
		return getIdea_title();
	}

	public Image getFk_img() {
		return fk_img;
	}

	public void setFk_img(Image fk_img) {
		this.fk_img = fk_img;
	}

	public Idea() {
		super();
	}
}
