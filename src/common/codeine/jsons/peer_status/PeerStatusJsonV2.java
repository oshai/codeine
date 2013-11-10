package codeine.jsons.peer_status;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Maps;


public class PeerStatusJsonV2 {
	private String peer_key;
	private String peer_host_port;
	private Map<String, ProjectStatus> project_name_to_status = Maps.newHashMap();//Lists.newArrayList();
	private String host;
	private int port;
	private String version;
	private String tar;
	private long start_time;
	private long update_time;//updated in directory server when first seen
	private String install_dir;
	
	
	public PeerStatusJsonV2(String host, int port, String version, long start_time, String install_dir, String tar, Map<String, ProjectStatus> project_name_to_status) {
		super();
		this.host = host;
		this.port = port;
		this.version = version;
		this.start_time = start_time;
		this.install_dir = install_dir;
		this.tar = tar;
		this.project_name_to_status = project_name_to_status;
		this.update_time = System.currentTimeMillis();
		this.peer_key = host + ":" + install_dir;
		this.peer_host_port = host + ":" + port;
	}	
	
	public void addProjectStatus(String name, ProjectStatus status) {
		HashMap<String, ProjectStatus> tempList = Maps.newHashMap(project_name_to_status);
		tempList.put(name, status);
		project_name_to_status = tempList;
	}
	
	public Map<String, ProjectStatus> project_name_to_status() {
		return Collections.unmodifiableMap(project_name_to_status);
	}
	
	public String peer_key() {
		return peer_key;
	}
	
	public String host_port() {
		return host + ":" + port;
	}

	public long update_time() {
		return update_time;
	}

	public boolean isExpired() {
		long lastUpdate = System.currentTimeMillis() - update_time(); 
		return lastUpdate > TimeUnit.DAYS.toMillis(3);
	}

	public String key() {
		return peer_key();
	}

	public String version() {
		return version;
	}

	public String host() {
		return host;
	}

	public String tar() {
		return tar;
	}
	
	
}