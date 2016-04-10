package Vista;

import java.io.RandomAccessFile;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class CanvasArbol extends Canvas {

	private static final long serialVersionUID = 1L;
	private Image imagen;
	private Graphics graficas;
	private RandomAccessFile a;
	private int baseE;

	public CanvasArbol() {
		baseE=400;
	}
	
	@Override
	public void update(Graphics g) {
		imagen = createImage(this.getWidth(), this.getHeight());
		graficas= imagen.getGraphics();
		graficas.setColor(Color.white);
		graficas.fillRect(0, 0, this.getWidth(), this.getHeight());
		pintarCursor();			//pintar tabla estudiante
		pintarEstudiante();
		g.drawImage(imagen, 0, 0, this);
	}

	private void pintarCursor() {
		try {
			a= new RandomAccessFile("arbol.dat", "rw");
			int lado = 20,ay,ab;
			long l = a.length()/20;
			graficas.setColor(Color.red);
			graficas.drawString("N", (lado * 2)+3 , lado-3);
			graficas.drawString("bal", (lado * 4)+3, lado-3);
			graficas.drawString("izq", (lado * 6)+3, lado-3);
			graficas.drawString("der", (lado * 8)+3, lado-3);
			graficas.drawString("ref", (lado * 10)+3, lado-3);
			a.seek(0);
			graficas.setColor(Color.black);
			for (int i = 0; i < l ; i++) {
				graficas.drawString("->"+i, 3, ((i+2)*lado)-3);
				graficas.drawRect(2 *lado, lado*(i+1), 2*lado, lado);
				ay=a.readInt();
				graficas.drawString(ay+"", (lado * 2)+3, ((i + 2) * lado)-3);
				graficas.drawRect(4 *lado, lado*(i+1), 2*lado, lado);
				graficas.drawString(a.readInt() + "", (lado * 4)+3, ((i + 2) * lado)-3);
				graficas.drawRect(6 *lado, lado*(i+1), 2*lado, lado);
				graficas.drawString(a.readInt() + "", (lado * 6)+3, ((i + 2) * lado)-3);
				graficas.drawRect(8 *lado, lado*(i+1), 2*lado, lado);
				graficas.drawString(a.readInt() + "", (lado * 8)+3, ((i + 2) * lado)-3);
				graficas.drawRect(10 *lado, lado*(i+1), 2*lado, lado);
				ab=a.readInt();
				graficas.drawString(ab + "", (lado * 10)+3, ((i+ 2) * lado)-3);
				if(ay!=0 && i!=0){
					graficas.drawLine(lado*12, ((i+ 2) * lado)-10, baseE, ((ab+ 2) * lado)-10);
				}
			}
			a.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void pintarEstudiante(){
		try{		
			a= new RandomAccessFile("estudiantes.dat", "rw");
			int lado = 20;
			byte[] b=new byte[10];
			long l = a.length()/22;
			graficas.setColor(Color.red);
			graficas.drawString("Nombre", baseE+(lado * 2)+3 , lado-3);
			graficas.drawString("N1",baseE+ (lado * 8)+3, lado-3);
			graficas.drawString("N2", baseE+(lado * 10)+3, lado-3);
			graficas.drawString("N3", baseE+(lado * 12)+3, lado-3);
			a.seek(0);
			graficas.setColor(Color.black);
			for (int i = 0; i < l ; i++) {
				graficas.drawString("->"+i, baseE+3, ((i+2)*lado)-3);
				
				graficas.drawRect(baseE+(2 *lado), lado*(i+1), 8*lado, lado);
				a.read(b);
				graficas.drawString(new String(b), baseE+(lado * 2)+3, ((i + 2) * lado)-3);
				
				graficas.drawRect(baseE+(8 *lado), lado*(i+1), 2*lado, lado);
				graficas.drawString(a.readInt() + "", baseE+(lado * 8)+3, ((i + 2) * lado)-3);
				
				graficas.drawRect(baseE+(10 *lado), lado*(i+1), 2*lado, lado);
				graficas.drawString(a.readInt() + "", baseE+(lado * 10)+3, ((i + 2) * lado)-3);
				
				graficas.drawRect(baseE+(12 *lado), lado*(i+1), 2*lado, lado);
				graficas.drawString(a.readInt() + "", baseE+(lado * 12)+3, ((i + 2) * lado)-3);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
