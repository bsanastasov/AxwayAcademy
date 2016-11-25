package com.axway.academy.blagolaj;

public class EncryptFile implements Request {

	private FileOperation fop;

	public EncryptFile(FileOperation fop) {
		this.fop = fop;
	}

	@Override
	public void execute() {
		fop.encrypt();
	}

}
