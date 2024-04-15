package member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MemberServiceTest {

    @Test
    public void testCreateMember() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        MemberModel memberModel = new MemberModel(11, "John", 100);

        // When
        memberService.createMember(memberModel);

        // Then
        assertEquals("John", memberDAO.getMember(11).getMemberName());
    }

    @Test
    public void testCreateDuplicateMember() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        MemberModel memberModel = new MemberModel(11, "John", 100);

        // When
        memberService.createMember(memberModel); // 첫 번째 회원 생성
        try {
            memberService.createMember(memberModel); // 두 번째 회원 생성 시도
            fail("Expected IllegalArgumentException was not thrown"); // 예외가 발생하지 않으면 테스트 실패
        } catch (IllegalArgumentException e) {
            // Then: 예외가 발생했음을 확인
            assertEquals("Member with ID 11 already exists.", e.getMessage());
        }
    }

    @Test
    public void testDeleteMember() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        memberDAO.createMember(new MemberDTO(11, "John", 100));

        // When
        memberService.deleteMember(11);

        // Then
        assertNull(memberDAO.getMember(11));
    }

    @Test
    public void testGetMember() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        MemberModel expectedMemberModel = new MemberModel(1, "John", 100);
        memberDAO.createMember(new MemberDTO(1, "John", 100));

        // When
        MemberModel actualMemberModel = memberService.getMember(1);

        // Then
        assertEquals(expectedMemberModel, actualMemberModel);
    }

    @Test
    public void testGetAllMembers() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        List<MemberModel> expectedMembers = new ArrayList<>();
        expectedMembers.add(new MemberModel(11, "John", 100));
        expectedMembers.add(new MemberModel(12, "Alice", 200));
        expectedMembers.add(new MemberModel(13, "Bob", 300));

        memberDAO.createMember(new MemberDTO(11, "John", 100));
        memberDAO.createMember(new MemberDTO(12, "Alice", 200));
        memberDAO.createMember(new MemberDTO(13, "Bob", 300));

        // When
        List<MemberModel> actualMembers = memberService.getAllMembers();

        // Then
        assertEquals(expectedMembers.size(), actualMembers.size());
        for (int i = 0; i < expectedMembers.size(); i++) {
//            assertEquals(expectedMembers.get(i), actualMembers.get(i));
            System.out.println(actualMembers.get(i).getMemberName());
        }
    }

    // 테스트용 MemberDAO 구현체
    private class TestMemberDAO implements MemberDAO {
        private MemberDTO member;
        private List<MemberDTO> members = new ArrayList<>();

        @Override
        public void createMember(MemberDTO member) {
            this.member = member;
            members.add(member);
        }

        @Override
        public void deleteMember(int memberId) {
            for (MemberDTO member : members) {
                if (member.getMemberId() == memberId) {
                    members.remove(member);
                    break;
                }
            }
        }

        @Override
        public void updateMember(MemberDTO member) {
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).getMemberId() == member.getMemberId()) {
                    members.set(i, member);
                    break;
                }
            }
        }

        @Override
        public MemberDTO getMember(int memberId) {
            for (MemberDTO member : members) {
                if (member.getMemberId() == memberId) {
                    return member;
                }
            }
            return null;
        }

        @Override
        public List<MemberDTO> getAllMembers() {
            return members;
        }
    }
}