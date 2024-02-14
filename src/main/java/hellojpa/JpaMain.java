package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // Start transaction

        try {
//             Member member = new Member();
//             member.setId(2L);
//             member.setName("1L");
//             em.persist(member); // Save member

            // Example of retrieving and updating a member
//             Member findMember = em.find(Member.class, 1L);
//             findMember.setName("Hellojpa");

            // Querying all members
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result){
                System.out.println("Member.name :" + member.getName());
            }
            tx.commit(); // Commit transaction
        } catch (Exception e) {
            tx.rollback(); // Rollback transaction in case of exception
        } finally {
            em.close(); // Close EntityManager
        }
        emf.close(); // Close EntityManagerFactory
    }
}
