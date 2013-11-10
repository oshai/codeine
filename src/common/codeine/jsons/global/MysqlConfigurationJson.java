package codeine.jsons.global;

public class MysqlConfigurationJson {

	private String host;
	private Integer port;
	private String dir;
	private String bin_dir;
	
	public String dir() {
		return dir;
	}

	public Integer port() {
		return port;
	}

	public String host() {
		return host;
	}

	public String bin_dir() {
		return bin_dir;
	}

	@Override
	public String toString() {
		return "MysqlConfigurationJson [host=" + host + ", port=" + port + ", dir=" + dir + ", bin_dir=" + bin_dir
				+ "]";
	}
	
	
}