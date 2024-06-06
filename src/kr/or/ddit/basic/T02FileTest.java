package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) throws IOException {
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if (f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");
			
			if (f1.createNewFile()) {
				System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
			}
		}
		
		if (f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다 .");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("-----------------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();
		
		for (File f : files) {
			System.out.println(f.getName() + " => ");
			
			if (f.isFile()) {
				System.out.println("파일");
			} else if (f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("=================================");
		String[] strFiles = f3.list();
		for (String name : strFiles) {
			System.out.println(name);
		}
		System.out.println("---------------------------------");
		System.out.println();
		
		displayFileList(new File("d:/D_Other"));
	}

	// 지정한 디렉토리(경로)에 포함된 파일과 디렉토리 목록을 보여주기 위한 메서드
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용");
		
		// 디렉토리 안의 모든 파일 목록을 가져온다
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보를 저장하기 위한 List 객체 생성(File 배열을 인덱스값 저장용)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		// 날짜정보를 출력하기 위한 포맷터 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for (int i=0; i<files.length; i++) {
			String attr = "";	// 파일 속성정보(읽기권한, 쓰기권한, 숨김파일, 폴더 등)
			String size = "";	// 파일 용량
			
			if (files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i);
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " ";		// canRead : 읽을 수 있는가?
				attr += files[i].canWrite() ? "W" : " ";	// canWrite : 쓸 수 있는가?
				attr += files[i].isHidden() ? "H" : " ";	// isHidden : 숨긴 파일인가?
			}
			
			System.out.printf("%s %-5s %12s %s\n",
					sdf.format(new Date(files[i].lastModified())),		// lastModified : 마지막 수정 시간
					attr, size, files[i].getName());
		}
		
		int dirCount = subDirList.size();	// 폴더 개수
		int fileCount = files.length - dirCount;	// 파일 개수
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
	}
}
