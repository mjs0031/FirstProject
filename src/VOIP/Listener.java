// Package Declaration //
package VOIP;

//Java Package Support //
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

//Internal Package Support //
// { Not Applicable }

/**
* 
* VOIP/Listener.java
* 
* @author(s)	: Ian Middleton, Zach Ogle, Matthew J Swann
* @version  	: 1.0
* Last Update	: 2013-02-20
* Update By		: Matthew J Swann
* 
* 
* VOIP PACKAGE :: Source code for Comp 6360: Wireless & Mobile Networks
* 	               Assignment 1 :: VOIP
* 
*/

public class Listener{
	
	
	/**
	 * 
	 * 
	 * @param  args
	 * @throws LineUnavailableException
	 */
	public static void main(String[] args) throws LineUnavailableException{		
		TargetDataLine tLine = null;
		SourceDataLine sLine = null;
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
				16, 2, 4, 44100, false);		
		
		DataLine.Info tLineInfo = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sLineInfo = new DataLine.Info(SourceDataLine.class, format);
		
		tLine = (TargetDataLine)AudioSystem.getLine(tLineInfo);
		sLine = (SourceDataLine)AudioSystem.getLine(sLineInfo);
		
		tLine.open(format);
		sLine.open(format);
		
		tLine.start();
		sLine.start();
		
		byte[] buffer = new byte[2048];
		
		int numBytes = 0;
		
		// Continues until program is closed.
		while(true){
			numBytes = tLine.read(buffer, 0, buffer.length);
			sLine.write(buffer, 0, numBytes);
		} // end_while
	} // end_main()
} // end_class_declaration