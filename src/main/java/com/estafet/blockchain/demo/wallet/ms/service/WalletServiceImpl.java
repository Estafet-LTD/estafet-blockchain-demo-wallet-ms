package com.estafet.blockchain.demo.wallet.ms.service;

import com.estafet.blockchain.demo.messages.lib.wallet.UpdateWalletBalanceMessage;
import com.estafet.blockchain.demo.wallet.ms.dao.WalletDAO;
import com.estafet.blockchain.demo.wallet.ms.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletDAO walletDAO;

    @Override
    @Transactional(readOnly = true)
    public Wallet getWallet(String walletAddress) {
        return walletDAO.getWallet(walletAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wallet> getWallets() {
        return walletDAO.getWallets();
    }

    @Override
    @Transactional
    public Wallet createWallet(Wallet wallet) {
        return walletDAO.createWallet(wallet);
    }

    @Override
    @Transactional
    public Wallet walletToWalletTransfer(String walletAddress, int cryptoAmount) {
       return walletDAO.walletToWalletTransfer(walletAddress, cryptoAmount);
    }

    @Override
    @Transactional
    public Wallet bankToWalletTransfer(String walletAddress, double amount) {
        return walletDAO.bankToWalletTransfer(walletAddress, amount);
    }

    @Override
    public void handleUpdateWalletBalanceMessage(UpdateWalletBalanceMessage message) {
        Wallet wallet = getWallet(message.getWalletAddress());
        wallet.setStatus("CLEARED");
        wallet.setBalance(message.getBalance());
        walletDAO.updateWallet(wallet);
    }

    @Autowired
    public void setWalletDAO(WalletDAO walletDAO) {
        this.walletDAO = walletDAO;
    }
}