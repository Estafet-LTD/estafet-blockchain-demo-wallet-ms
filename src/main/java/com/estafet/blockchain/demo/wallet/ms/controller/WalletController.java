package com.estafet.blockchain.demo.wallet.ms.controller;

import com.estafet.blockchain.demo.wallet.ms.model.Account;
import com.estafet.blockchain.demo.wallet.ms.model.Wallet;
import com.estafet.blockchain.demo.wallet.ms.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estafet.blockchain.demo.wallet.ms.model.API;

import java.util.List;

@RestController
public class WalletController {

	@Value("${app.version}")
	private String appVersion;

	@Autowired
	private WalletService walletService;
	
	@GetMapping("/api")
	public API getAPI() {
		return new API(appVersion);
	}

	@GetMapping("/wallet/{walletAddress}")
	public Wallet getWallet(@PathVariable String walletAddress) {
		return walletService.getWallet(walletAddress);
	}

	@GetMapping(value = "/wallets")
	public List<Wallet> getWallets() {
		return walletService.getWallets();
	}

	@PostMapping("/wallet")
	public ResponseEntity<Wallet> createWallet(@RequestBody Account account) {
		return new ResponseEntity<>(walletService.createWallet(account), HttpStatus.OK);
	}

	@PostMapping("/wallet/from/{fromWalletAddress}/to/{toWalletAddress}/crypto-transfer/{cryptoAmount}")
	public ResponseEntity<Wallet> walletToWalletTransfer(@PathVariable String fromWalletAddress,
														 @PathVariable String toWalletAddress,
														 @PathVariable int cryptoAmount) {
		return new ResponseEntity<>(walletService.walletToWalletTransfer(fromWalletAddress,toWalletAddress,cryptoAmount), HttpStatus.OK);
	}

	@PostMapping("/wallet/{walletAddress}/currency-transfer/{amount}")
	public ResponseEntity<Wallet> bankToWalletTransfer(@PathVariable String walletAddress, @PathVariable double amount) {
		return new ResponseEntity<>(walletService.bankToWalletTransfer(walletAddress,amount), HttpStatus.OK);
	}
}
