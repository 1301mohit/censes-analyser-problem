package com.csvfile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder{
    @Override
    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        CsvToBean<E> csvToBean = this.getCSVBean(reader, csvClass);
        return csvToBean.iterator();
    }

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
        CsvToBean<E> csvToBean = this.getCSVBean(reader, csvClass);
        return csvToBean.parse();
    }

    private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        }  catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(),
                                          CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
