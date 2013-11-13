/*
		Author : Ahmet DEMIR
		Version 1.0
		Date : May 2011
		Description : Java ADB Explorer allows you to explore your Anroid Phone.
		under License GPL: http://www.gnu.org/copyleft/gpl.html
		-----------------------------------------------------------
		Copyright (C) 2011 Ahmet DEMIR

 		This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/


package adbexplorer.util;

public class FileType {

	private String name;
	private String path;
	private int type; //1 file, 2 directory, 3 link, -1 denied file, -2 denied directory, -3 denied link
	private String permissions;
	
	
	public FileType() {
		this.name = "";
		this.path = "";
		this.type = 0;
		this.permissions = "";
	}
	
	public FileType(String name, String path, int type, String permissions_s) {
		super();
		this.name = name;
		this.path = path;
		this.type = type;
		this.permissions = toNumericRepresentation(permissions_s);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return 1 file, 2 directory, 3 link, -1 denied file, -2 denied directory, -3 denied link
	 */
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		//return "ADBObject [name=" + name + ", path=" + path + ", type=" + type + ", permissions=" + permissions + "]";
		return name;

	}
	
	public String toFullString() {
		return "ADBObject [name=" + name + ", path=" + path + ", type=" + type + ", permissions=" + permissions + "]";
	}
	
	public int permissionToInteger(String permissions_s) {
		int retour = 0;
		try {
			retour = Integer.parseInt(toNumericRepresentation(permissions_s));
		}
		catch (Exception e) {
			retour = 0;
		}
		return retour;
	}
	
	private String toNumericRepresentation(String permissions_s) {
		String user = toNumericRepresentationHelper(permissions_s.substring(0,3))==0?"0":""+toNumericRepresentationHelper(permissions_s.substring(0,3));
		String group = toNumericRepresentationHelper(permissions_s.substring(3,6))==0?"0":""+toNumericRepresentationHelper(permissions_s.substring(3,6));
		String other = toNumericRepresentationHelper(permissions_s.substring(6,9))==0?"0":""+toNumericRepresentationHelper(permissions_s.substring(6,9));

		return user+group+other;
	}
	
	private int toNumericRepresentationHelper(String perm) {
		int read = perm.substring(0, 1).equals("-")?0:1;
		int write = perm.substring(1, 2).equals("-")?0:1;
		int exec = perm.substring(2, 3).equals("-")?0:1;
		
		return (read*4)+(write*2)+exec;
		
	}
}
