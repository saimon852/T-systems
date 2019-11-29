
public class Dom {
	private int pocetizieb;
	private String farba;
	private String pes;

	public Dom(String farba, String pes, int pocetizieb) {
		this.farba = farba;
		this.pes = pes;
		this.pocetizieb = pocetizieb;
	}

	public void describe() {
		System.out.println("Mam pekny " + farba + " " + "dom " + pes + pocetizieb);
		
	}
}
