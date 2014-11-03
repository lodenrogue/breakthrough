package com.littlebandit.breakthrough.gameutilities.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Converts a Tiled .lua output file into a game .map file.
 * 
 * @author Miguel Hernandez
 *
 */

public class TiledConverter {
	private ArrayList<String> lines = new ArrayList<String>();
	private String path;

	public TiledConverter() {
		path = this.getClass().getClassLoader().getResource("").getPath();
		path = path.substring(0, path.indexOf("core"));
		path += "android/assets/";
	}

	public static void main(String[] args) {

		TiledConverter t = new TiledConverter();
		System.out.println();
		t.convert("tiledOutput.lua", "level1.map");
	}

	/**
	 * Converts a given file into a map level.
	 * 
	 * @param fileName Input file containing data to be converted.
	 * @param targetFileName Output file name where converted data is to be
	 *                written.
	 */
	public void convert(String fileName, String targetFileName) {
		lines = readFile(fileName);
		lines = cleanSpaces(lines);
		lines = changeGlyphs(lines);
		export(targetFileName, lines);

		System.out.println(path + targetFileName + " written with the following data: ");
		for (String s : lines) {
			System.out.println(s);
		}
	}

	private void export(String targetFileName, ArrayList<String> outputLines) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path + targetFileName));
			for (int i = 0; i < outputLines.size() - 2; i++) {
				out.write(outputLines.get(i) + "\n");
			}
			out.write(outputLines.get(outputLines.size() - 1));
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> changeGlyphs(ArrayList<String> array) {
		ArrayList<String> output = new ArrayList<String>();

		for (String s : array) {
			String tmp = s;

			// Do double digit numbers first
			tmp = tmp.replace("1", "[B]");
			tmp = tmp.replace("0", "[x]");

			// Remove comma last
			tmp = tmp.replace(",", "");

			output.add(tmp);
		}

		return output;
	}

	private ArrayList<String> cleanSpaces(ArrayList<String> array) {
		ArrayList<String> output = new ArrayList<String>();

		for (String s : array) {
			String tmp = s;
			tmp = tmp.replace(" ", "");
			output.add(tmp);
		}

		return output;
	}

	private ArrayList<String> readFile(String fileName) {
		ArrayList<String> inputLines = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(path + fileName));
			String line;
			boolean record = false;

			while ((line = in.readLine()) != null) {
				if (line.contains("data =")) {
					line = in.readLine();
					record = true;
				}
				if (record) {
					if (!line.contains("}")) {
						inputLines.add(line);
					}
				}
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return inputLines;
	}

}
