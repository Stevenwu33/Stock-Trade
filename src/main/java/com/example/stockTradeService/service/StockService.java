package com.example.stockTradeService.service;

import com.example.stockTradeService.models.Stock;
import com.example.stockTradeService.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    public final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }




//    public List<Stock> getAllByCurrentPriceAsc(){
//        return stockRepository.getAllByCurrentPriceAsc();
//    }
//
//    public List<Stock> getAllByCurrentPriceDesc(){
//        return stockRepository.getAllByCurrentPriceDesc();
//    }

//    public Stock findByTicker(String ticker){
//        return stockRepository.findByTicker(ticker);   //apply try catch safety last
//    }


}
