package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileUtils {

	private final static Logger LOGGER = Logger.getLogger(FileUtils.class);

	public boolean createFile(String name, String pfade) {
		try {
			File file = new File(pfade + "\\" + name);
			if (file.createNewFile()) {
				LOGGER.info("File is created!");
				return true;
			} else {
				LOGGER.error("File already exists.");
				return false;
			}
		} catch (IOException e) {
			LOGGER.error(e);
			return false;
		}
	}

	public boolean removeFile(File file) {
		if (file != null) {
			if (file.isFile()) {
				if (file.delete()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean renameFile(File file, String newname) {
		if (file != null) {
			if (file.isFile()) {
				if (file.renameTo(new File(newname))) {
					LOGGER.info("File is renamed successful!");
					return true;
				} else {
					LOGGER.info("File is renamed to move!");
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean moveFile(File file, String target) {
		if (file != null) {
			if (file.isFile()) {
				if (file.renameTo(new File(target + "\\" + file.getName()))) {
					LOGGER.info("File is moved successful!");
					return true;
				} else {
					LOGGER.info("File is failed to move!");
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean copyAndDeletFile(File file, String target) {
		if (file != null) {
			InputStream inStream = null;
			OutputStream outStream = null;
			try {
				File bfile = new File(target + "\\" + file.getName());
				inStream = new FileInputStream(file);
				outStream = new FileOutputStream(bfile);
				byte[] buffer = new byte[1024];
				int length;
				// copy the file content in bytes
				while ((length = inStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, length);
				}
				inStream.close();
				outStream.close();
				// delete the original file
				file.delete();
				LOGGER.info("File is copied successful!");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.info("Error by coping!");
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean copyFile(File file, String target) {
		if (file != null) {
			InputStream inStream = null;
			OutputStream outStream = null;
			try {
				File bfile = new File(target + "\\" + file.getName());
				inStream = new FileInputStream(file);
				outStream = new FileOutputStream(bfile);
				byte[] buffer = new byte[1024];
				int length;
				// copy the file content in bytes
				while ((length = inStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, length);
				}
				inStream.close();
				outStream.close();
				LOGGER.info("File is copied successful!");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.info("Error by coping!");
				return false;
			}
		} else {
			return false;
		}
	}

	public List<File> allFileinDirectory(String dir) {
		List<File> listFile = new ArrayList<File>();
		File file = new File(dir);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory() == true) {
						LOGGER.info("Dossier: " + files[i].getAbsolutePath());
						allFileinDirectory(files[i].getAbsolutePath());
					} else {
						LOGGER.info("  Fichier: " + files[i].getName());
						listFile.add(files[i]);
					}
				}
			}
		}
		return listFile;
	}

	public void createCsvFile(String pfade, String name, List<List<String>> values, char separators) {
		try {
			FileWriter writer = new FileWriter(pfade + "\\" + name);

			for(List<String> lst : values) {
				writeLine(lst, separators, writer);		
			}
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLine(List<String> values, char separators, FileWriter writer) throws IOException {
		boolean first = true;
		char DEFAULT_SEPARATOR = ',';

		if (separators == ' ') {
		    separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
		    if (!first) {
		        sb.append(separators);
		    } 
		    sb.append(value);
		    
		    first = false;
		}
		sb.append("\n");
		writer.append(sb.toString());
	}

	public List<List<String>> readCSVFile(String filepath) {
		List<List<String>> values = new ArrayList<List<String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(filepath));
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(cvsSplitBy);
				List<String> list = new ArrayList<String>();
				for(int i=0; i < tokens.length ; i++) {
					list.add(tokens[i]);
				}
				values.add(list);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("Le fichier spécifié est introuvable ", e);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
		return values;
	}
	
	public boolean deleteFileorDirectory(String str) {
		File dir = new File(str);
		if(dir.isFile()) {
			dir.delete();
		}
		if(dir.isDirectory()) {
			List<File> lstFile = allFileinDirectory(str);
			for(File file : lstFile) {
				file.delete();
			}
			dir.delete();
		}		
		return true;
	}
	
}