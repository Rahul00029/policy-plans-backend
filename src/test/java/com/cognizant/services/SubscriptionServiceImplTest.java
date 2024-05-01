package com.cognizant.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.cognizant.dto.SubscriptionDTO;
import com.cognizant.entities.Policy;
import com.cognizant.entities.Subscription;
import com.cognizant.repositories.PolicyRepository;
import com.cognizant.repositories.SubscriptionRepository;
import com.cognizant.services.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.Optional;

class SubscriptionServiceImplTest {
	@Mock
	private SubscriptionRepository subscriptionRepository;
	@Mock
	private PolicyRepository policyRepository;
	@InjectMocks
	private SubscriptionServiceImpl subscriptionService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void addNewSubscriptionPositive() {
		try{

			Optional<Policy> mockOptionalOfPolicy= Mockito.mock(Optional.class);
			Optional<Subscription> mockOptionalOfSubscription= Mockito.mock(Optional.class);
			Policy mockPolicy=Mockito.mock(Policy.class);
            Subscription mockSubscription=Mockito.mock(Subscription.class);

			SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
			subscriptionDTO.setSubscriptionId(1000);
			subscriptionDTO.setSubscriptionDate("2024-03-10");
			subscriptionDTO.setSubscriptionStatus("New");
			subscriptionDTO.setPolicyId(1);
			subscriptionDTO.setUsername("Ram");
			subscriptionDTO.setMedicalCertificateDocURL("www.google.docs/sfewrw234");
			subscriptionDTO.setPolicyHolderName("Ram");
			subscriptionDTO.setRelationToPolicyHolder("parent");

			when(policyRepository.findById(anyInt())).thenReturn(mockOptionalOfPolicy);
			when(mockOptionalOfPolicy.isPresent()).thenReturn(true);
			when(mockOptionalOfPolicy.get()).thenReturn(mockPolicy);

            when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(mockSubscription);
            SubscriptionDTO sub=subscriptionService.addNewSubscription(2,subscriptionDTO);
			System.out.println(sub);
			assertNotNull(sub);
		}catch (Exception e){
			System.out.println(e);
			assertTrue(false);
		}
	}

    @Test
    void addNewSubscriptionWhenSubscriptionDTOIsNull()
    {
        try {
            SubscriptionDTO subscriptionDTO=subscriptionService.addNewSubscription(1,null);
            assertNull(subscriptionDTO);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    void subscriptionExistsPositiveWhenPolicyHolderNameMatches()
    {
        try {
            Iterable<Subscription> mockIterableOfSubscription = Mockito.mock(Iterable.class);
            Iterator<Subscription> mockIteratorOfSubscription = Mockito.mock(Iterator.class);
            when(subscriptionRepository.findByUsername(anyString())).thenReturn(mockIterableOfSubscription);
            when(mockIterableOfSubscription.iterator()).thenReturn(mockIteratorOfSubscription);
            when(mockIteratorOfSubscription.hasNext()).thenReturn(true).thenReturn(false);
            Subscription mockSubscription=Mockito.mock(Subscription.class);
            when(mockIteratorOfSubscription.next()).thenReturn(mockSubscription);
            when(mockSubscription.getPolicyHolderName()).thenReturn("Ram");

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setSubscriptionId(1000);
            subscriptionDTO.setSubscriptionDate("2024-03-10");
            subscriptionDTO.setSubscriptionStatus("New");
            subscriptionDTO.setPolicyId(1);
            subscriptionDTO.setUsername("Ram");
            subscriptionDTO.setMedicalCertificateDocURL("www.google.docs/sfewrw234");
            subscriptionDTO.setPolicyHolderName("Ram");
            subscriptionDTO.setRelationToPolicyHolder("parent");

            Boolean actual = subscriptionService.subscriptionExists(subscriptionDTO);
            assertTrue(actual);
        }catch (Exception e){
            assertTrue(false);
        }

    }

    @Test
    void subscriptionExistsPositiveWhenRelationToPolicyHolderIsParent()
    {
        try {
            Iterable<Subscription> mockIterableOfSubscription = Mockito.mock(Iterable.class);
            Iterator<Subscription> mockIteratorOfSubscription = Mockito.mock(Iterator.class);
            when(subscriptionRepository.findByUsername(anyString())).thenReturn(mockIterableOfSubscription);
            when(mockIterableOfSubscription.iterator()).thenReturn(mockIteratorOfSubscription);
            when(mockIteratorOfSubscription.hasNext()).thenReturn(true).thenReturn(false);
            Subscription mockSubscription=Mockito.mock(Subscription.class);
            when(mockIteratorOfSubscription.next()).thenReturn(mockSubscription);
            when(mockSubscription.getPolicyHolderName()).thenReturn("Shyam");
            when(mockSubscription.getRelationToPolicyHolder()).thenReturn("parent");

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setSubscriptionId(1000);
            subscriptionDTO.setSubscriptionDate("2024-03-10");
            subscriptionDTO.setSubscriptionStatus("New");
            subscriptionDTO.setPolicyId(1);
            subscriptionDTO.setUsername("Ram");
            subscriptionDTO.setMedicalCertificateDocURL("www.google.docs/sfewrw234");
            subscriptionDTO.setPolicyHolderName("Ram");
            subscriptionDTO.setRelationToPolicyHolder("parent");

            Boolean actual = subscriptionService.subscriptionExists(subscriptionDTO);
            assertTrue(actual);
        }catch (Exception e){
            assertTrue(false);
        }

    }

    @Test
    void subscriptionExistsPositiveWhenRelationToPolicyHolderIsSpouse()
    {
        try {
            Iterable<Subscription> mockIterableOfSubscription = Mockito.mock(Iterable.class);
            Iterator<Subscription> mockIteratorOfSubscription = Mockito.mock(Iterator.class);
            when(subscriptionRepository.findByUsername(anyString())).thenReturn(mockIterableOfSubscription);
            when(mockIterableOfSubscription.iterator()).thenReturn(mockIteratorOfSubscription);
            when(mockIteratorOfSubscription.hasNext()).thenReturn(true).thenReturn(false);
            Subscription mockSubscription=Mockito.mock(Subscription.class);
            when(mockIteratorOfSubscription.next()).thenReturn(mockSubscription);
            when(mockSubscription.getPolicyHolderName()).thenReturn("Shyam");
            when(mockSubscription.getRelationToPolicyHolder()).thenReturn("spouse");

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setSubscriptionId(1000);
            subscriptionDTO.setSubscriptionDate("2024-03-10");
            subscriptionDTO.setSubscriptionStatus("New");
            subscriptionDTO.setPolicyId(1);
            subscriptionDTO.setUsername("Ram");
            subscriptionDTO.setMedicalCertificateDocURL("www.google.docs/sfewrw234");
            subscriptionDTO.setPolicyHolderName("Ram");
            subscriptionDTO.setRelationToPolicyHolder("spouse");

            Boolean actual = subscriptionService.subscriptionExists(subscriptionDTO);
            assertTrue(actual);
        }catch (Exception e){
            assertTrue(false);
        }

    }

    @Test
    void subscriptionExistsPositiveWhenRelationToPolicyHolderIdProofMatches()
    {
        try {
            Iterable<Subscription> mockIterableOfSubscription = Mockito.mock(Iterable.class);
            Iterator<Subscription> mockIteratorOfSubscription = Mockito.mock(Iterator.class);
            when(subscriptionRepository.findByUsername(anyString())).thenReturn(mockIterableOfSubscription);
            when(mockIterableOfSubscription.iterator()).thenReturn(mockIteratorOfSubscription);
            when(mockIteratorOfSubscription.hasNext()).thenReturn(true).thenReturn(false);
            Subscription mockSubscription=Mockito.mock(Subscription.class);
            when(mockIteratorOfSubscription.next()).thenReturn(mockSubscription);
            when(mockSubscription.getPolicyHolderName()).thenReturn("Shyam");
            when(mockSubscription.getRelationToPolicyHolder()).thenReturn("child");
            when(mockSubscription.getPolicyHolderIdProofNo()).thenReturn("123456");

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setSubscriptionId(1000);
            subscriptionDTO.setSubscriptionDate("2024-03-10");
            subscriptionDTO.setSubscriptionStatus("New");
            subscriptionDTO.setPolicyId(1);
            subscriptionDTO.setUsername("Ram");
            subscriptionDTO.setMedicalCertificateDocURL("www.google.docs/sfewrw234");
            subscriptionDTO.setPolicyHolderName("Ram");
            subscriptionDTO.setRelationToPolicyHolder("child");
            subscriptionDTO.setPolicyHolderIdProofNo("123456");

            Boolean actual = subscriptionService.subscriptionExists(subscriptionDTO);
            assertTrue(actual);
        }catch (Exception e){
            assertTrue(false);
        }

    }
    @Test
    void subscriptionExistsNegative(){
        try {
            Iterable<Subscription> mockIterableOfSubscription = Mockito.mock(Iterable.class);
            Iterator<Subscription> mockIteratorOfSubscription = Mockito.mock(Iterator.class);
            when(subscriptionRepository.findByUsername(anyString())).thenReturn(mockIterableOfSubscription);
            when(mockIterableOfSubscription.iterator()).thenReturn(mockIteratorOfSubscription);
            when(mockIteratorOfSubscription.hasNext()).thenReturn(false);

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setSubscriptionId(1000);
            subscriptionDTO.setSubscriptionDate("2024-03-10");
            subscriptionDTO.setSubscriptionStatus("New");
            subscriptionDTO.setPolicyId(1);
            subscriptionDTO.setUsername("Ram");
            subscriptionDTO.setMedicalCertificateDocURL("www.google.docs/sfewrw234");
            subscriptionDTO.setPolicyHolderName("Ram");
            subscriptionDTO.setRelationToPolicyHolder("parent");


            Boolean actual = subscriptionService.subscriptionExists(subscriptionDTO);
            assertFalse(actual);
        }catch (Exception e){
            assertTrue(false);
        }

    }


    @Test
    void findByPolicyHolderIdProofNoPositive()
    {
        Optional<Subscription>mockOptional=Mockito.mock(Optional.class);
        when(subscriptionRepository.findByPolicyHolderIdProofNo(anyString())).thenReturn(mockOptional);
        when(mockOptional.isPresent()).thenReturn(true);
        boolean actual=subscriptionService.findByPolicyHolderIdProofNo(anyString());
        assertTrue(actual);
    }

    @Test
    void findByPolicyHolderIdProofNoNegative()
    {
        Optional<Subscription>mockOptional=Mockito.mock(Optional.class);
        when(subscriptionRepository.findByPolicyHolderIdProofNo(anyString())).thenReturn(mockOptional);
        when(mockOptional.isPresent()).thenReturn(false);
        boolean actual=subscriptionService.findByPolicyHolderIdProofNo(anyString());
        assertFalse(actual);
    }

}

