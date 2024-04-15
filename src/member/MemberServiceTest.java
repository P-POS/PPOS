package member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MemberServiceTest {

    @Test
    public void testCreateMember() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        MemberModel memberModel = new MemberModel(1, "John", 100);

        // When
        memberService.createMember(memberModel);

        // Then
        assertEquals("John", memberDAO.getMember(1).getMemberName());
    }

    @Test
    public void testDeleteMember() {
        // Given
        MemberDAO memberDAO = new TestMemberDAO(); // 외부 의존성이 있는 MemberDAO의 대체 구현체
        MemberService memberService = new MemberService(memberDAO);
        memberDAO.createMember(new MemberDTO(1, "John", 100));

        // When
        memberService.deleteMember(1);

        // Then
        assertNull(memberDAO.getMember(1));
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
        expectedMembers.add(new MemberModel(1, "John", 100));
        expectedMembers.add(new MemberModel(2, "Alice", 200));
        expectedMembers.add(new MemberModel(3, "Bob", 300));

        memberDAO.createMember(new MemberDTO(1, "John", 100));
        memberDAO.createMember(new MemberDTO(2, "Alice", 200));
        memberDAO.createMember(new MemberDTO(3, "Bob", 300));

        // When
        List<MemberModel> actualMembers = memberService.getAllMembers();

        // Then
        assertEquals(expectedMembers.size(), actualMembers.size());
        for (int i = 0; i < expectedMembers.size(); i++) {
            assertEquals(expectedMembers.get(i), actualMembers.get(i));
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
