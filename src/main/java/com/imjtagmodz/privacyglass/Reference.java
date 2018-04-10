package com.imjtagmodz.privacyglass;

public class Reference {
	public static final String MOD_ID = "prvglass";
	public static final String NAME = "Privacy Glass";
	public static final String VERSION = "1.0";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";

	public static final String CLIENT_PROXY_CLASS = "com.imjtagmodz.privacyglass.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.imjtagmodz.privacyglass.proxy.ServerProxy";

	public static enum ModItems {
		PRIVACYGLASS("privacyglass", "ItemPrivacyGlass");
		
		private String unlocalizedName;
		private String registryName;
		
		ModItems(String unlocalizedName, String registryName){
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
		
		public String getRegistryName() {
			return this.registryName;
		}
	}
}
