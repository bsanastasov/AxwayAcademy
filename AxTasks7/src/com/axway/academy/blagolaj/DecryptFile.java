package com.axway.academy.blagolaj;

public class DecryptFile implements Request {
	private FileOperation fop;

	public DecryptFile(FileOperation fop) {
		this.fop = fop;
	}

	@Override
	public void execute() {
		fop.decrypt();
	}

}
