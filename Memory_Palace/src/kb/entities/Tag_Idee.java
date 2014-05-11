package kb.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tag_Idee
 *
 */
@Entity

public class Tag_Idee implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ti_id")
	private int ti_id;
	
	@ManyToOne
	@JoinColumn(name="fk_idea")
	private Idee fk_idea;
	
	@ManyToOne
	@JoinColumn(name="fk_tag")
	private Tag fk_tag;
	
	
	public int getTi_id() {
		return ti_id;
	}

	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}

	public Idee getFk_idea() {
		return fk_idea;
	}

	public void setFk_idea(Idee fk_idea) {
		this.fk_idea = fk_idea;
	}

	public Tag getFk_tag() {
		return fk_tag;
	}

	public void setFk_tag(Tag fk_tag) {
		this.fk_tag = fk_tag;
	}

	public Tag_Idee() {
		super();
	}
}
