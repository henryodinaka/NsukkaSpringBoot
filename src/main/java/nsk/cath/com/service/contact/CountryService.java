package nsk.cath.com.service.contact;

import nsk.cath.com.enums.Continent;
import nsk.cath.com.model.contact.Country;
import nsk.cath.com.repo.contact.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private CountryRepo countryRepo;

    @Autowired
    public void setCountryRepo(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public List<Country> findAllCountries()
    {
        return countryRepo.findAllCountries();
    }

    public List<Country> getAllByContinent(Continent continent)
    {
        return countryRepo.getAllByContinent(continent);
    }

    public Page<Country> getAllByContinent(Continent continent, Pageable pageable)
    {
        return countryRepo.getAllByContinent(continent,pageable);
    }

}
