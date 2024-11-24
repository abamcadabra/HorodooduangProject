package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TarotDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tarot.db";
    private static final int DATABASE_VERSION = 15;

    public static final String TABLE_CARDS = "tarot_cards_career";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ADVICE = "advice";

    private final Map<Integer, String[]> tarotData = new HashMap<>();

    public TarotDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database", "Current DATABASE_VERSION: " + DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {




        db.execSQL("CREATE TABLE tarot_career (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE_ID + " INTEGER NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                "name_th TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                "description_th TEXT NOT NULL, " +
                COLUMN_ADVICE + " TEXT NOT NULL, " +
                "advice_th TEXT NOT NULL)");


// เพิ่มข้อมูลตัวอย่าง
        db.execSQL("INSERT INTO tarot_career (image_id, name,name_th, description,description_th, advice,advice_th) VALUES " +
                "(2131230863, 'The Fool', 'ไพ่โง่เขลา', " +
                "'A fresh start, new work, or an unexplored opportunity.', 'การเริ่มต้นใหม่ งานใหม่ หรือโอกาสที่ไม่เคยลอง', " +
                "'Take risks or try new things, even if the outcome is uncertain.', 'กล้าที่จะเสี่ยงหรือทดลองสิ่งใหม่ แม้อาจจะยังไม่ชัดเจนว่าผลลัพธ์จะเป็นอย่างไร')," +
                "(2131230897, 'The Magician', 'ไพ่นักมายากล', " +
                "'Versatility and utilizing available skills and resources for success.', 'ความสามารถรอบด้าน ใช้ทักษะที่มีเพื่อประสบความสำเร็จ', " +
                "'Trust your potential and take action.', 'เชื่อมั่นในศักยภาพของตนเองและลงมือทำ')," +
                "(2131230864,'The High Priestess','ไพ่สังฆราชหญิง'," + " 'Using intuition at work, waiting for the right moment.','การใช้สัญชาตญาณในการทำงาน รอจังหวะที่เหมาะสม'," +
                "'Trust your instincts, take your time analyzing the information, and avoid rushing.','พึ่งพาความรู้สึกภายในและอย่าเร่งรีบ ใช้เวลาในการวิเคราะห์ข้อมูล')," +
                "(2131230965,'The Empress','ไพ่จักรพรรดินี',"+" 'Growth, abundance, or creative opportunities.','การเติบโต ความอุดมสมบูรณ์ หรือโอกาสที่สร้างสรรค์',"+
                "'Use creativity in developing your work, especially in care or aesthetics-related fields.','ใช้ความคิดสร้างสรรค์ในการพัฒนางาน หรือทำงานที่เกี่ยวกับการดูแลหรือความสวยงาม')," +
                "(2131230964,'The Emperor','ไพ่จักรพรรดิ'," + "'Stability, structured management, or leadership.','ความมั่นคง การจัดการงานที่เป็นระบบ หรือความเป็นผู้นำ',"+
                "'Organize and control situations clearly.','จัดระเบียบและควบคุมสถานการณ์ให้ชัดเจน')," +
                "(2131230968,'The Hierophant','ไพ่สังฆราช',"+" 'Spiritual guidance, education, or traditional values.','การทำงานในองค์กรที่เป็นระบบ การเรียนรู้กฎเกณฑ์',"+
                "'Seek knowledge or advice from a mentor or trusted source.','ปฏิบัติตามแนวทางและคำแนะนำของผู้มีประสบการณ์')," +
                "(2131230969,'The Lovers', 'ไพ่คนรัก',"+"'Important choices or harmony in relationships.', ' การตัดสินใจที่สำคัญ หรือความร่วมมือกับคนอื่นในงาน',"+
                "'Follow your heart and consider your values when making decisions.','ให้ความสำคัญกับความสัมพันธ์ในที่ทำงานและเลือกทางที่สอดคล้องกับคุณค่าในใจ')," +
                "(2131230962,'The Chariot','ไพ่อัศวินรถศึก',"+" 'Determination and control.','ความมุ่งมั่นเพื่อความสำเร็จ การควบคุมและเดินหน้าต่อ',"+
                "'Stay focused on your goals and move forward with confidence.','จงแน่วแน่และจัดการปัญหาให้ลุล่วง')," +
                "(2131230959,'Strength','ไพ่พลัง',"+" 'Courage, patience, and inner strength.','ความอดทน การควบคุมอารมณ์ หรือการเผชิญกับความท้าทาย',"+
                " 'Use calmness and resilience to overcome challenges.','ใช้ความใจเย็นและความพยายามในงานที่ยากลำบาก')," +
                "(2131230967,'The Hermit','ไพ่ฤๅษี',"+"'Seeking inner wisdom and introspection.','การทำงานแบบลำพัง การค้นหาคำตอบในตัวเอง',"+
                " 'Take time to reflect and trust your inner guidance.','ใช้เวลาวิเคราะห์และหาทางออกจากภายใน')," +
                "(2131230977,'Wheel of Fortune','ไพ่กงล้อโชคชะตา',"+" 'Change and cycles.','โอกาสที่เปลี่ยนแปลงอย่างกะทันหัน การขึ้นลงของงาน',"+"" +
                " 'Be adaptable to life’s ups and downs.','เตรียมพร้อมรับมือกับการเปลี่ยนแปลงที่ไม่คาดคิด')," +
                "(2131230885 ,'Justice','ไพ่ความยุติธรรม',"+"'Fairness and truth.', 'การตัดสินใจอย่างเป็นธรรม หรือการทำงานที่เกี่ยวกับกฎหมาย',"+
                "'Act with integrity and seek balance in decisions.','ปฏิบัติตามกฎเกณฑ์และพิจารณาผลกระทบของการกระทำ')," +
                "(2131230966,'The Hanged Man','ไพ่คนห้อยหัว',"+" 'New perspective or letting go.','การหยุดชะงัก หรือมุมมองใหม่ในงาน',"+" " +
                "'Reassess priorities and be open to sacrifice for long-term gain.','ยอมรับสถานการณ์และมองหาวิธีการแก้ปัญหาแบบใหม่')," +
                "(2131230857 ,'Death','ไพ่แห่งความตาย',"+" 'Transformation and endings.','การเปลี่ยนแปลงครั้งใหญ่ การสิ้นสุดของบางสิ่งเพื่อเริ่มต้นใหม่',"+
                " 'Embrace change as a path to new beginnings.','ปล่อยวางสิ่งที่ไม่จำเป็นและมองหาโอกาสใหม่')," +
                "(2131230960,'Temperance','ไพ่ทางสายกลาง',"+" 'Balance and moderation.','การปรับสมดุล การทำงานร่วมกันอย่างมีประสิทธิภาพ',"+
                " 'Seek harmony in your actions and choices.','ปรับตัวและสร้างความสมดุลระหว่างชีวิตและงาน')," +
                "(2131230963,'The Devil','ไพ่ปีศาจ',"+" 'Materialism or entrapment.','การติดอยู่ในงานที่ไม่พอใจ หรือความกดดันในงาน',"+
                "'Break free from negative influences or attachments.','ระวังการพึ่งพาหรือพันธนาการที่ไม่ดีต่ออนาคต')," +
                "(2131230973,'The Tower','ไพ่หอคอยถล่ม',"+" 'Sudden upheaval or revelation.','การเปลี่ยนแปลงที่รุนแรงหรือเหตุการณ์ที่ไม่คาดฝันในงาน',"+
                " 'Adapt to unexpected changes and rebuild stronger.','ยอมรับการเปลี่ยนแปลงและปรับตัว')," +
                "(2131230971,'The Star','ไพ่ดวงดาว',"+" 'Hope, inspiration, and healing.', 'ความหวังและแรงบันดาลใจในงาน',"+
                "'Stay optimistic and trust in the future.','มีความเชื่อมั่นในอนาคตและอย่าหมดหวัง')," +
                "(2131230970,'The Moon','ไพ่พระจันทร์',"+" 'Illusion and intuition.','ความสับสนหรือความไม่ชัดเจนในงาน',"+
                " 'Trust your instincts and look beyond surface appearances.','ใช้สัญชาตญาณและมองหาความจริงในสิ่งที่ซ่อนอยู่')," +
                "(2131230972,'The Sun','ไพ่พระอาทิตย์',"+" 'Success, positivity, and vitality.','ความสำเร็จและความสุขในงาน',"+
                " 'Embrace happiness and share your joy with others.','โอกาสที่ดีมาถึงแล้ว ใช้ความกระตือรือร้นให้เต็มที่')," +
                "(2131230884,'Judgement','ไพ่พิพากษา',"+" 'Reflection and awakening.','การประเมินผลงานหรือการตัดสินใจครั้งสำคัญ',"+
                " 'Learn from the past and move forward with clarity.','ทบทวนอดีตและใช้โอกาสนี้เปลี่ยนแปลงตนเอง')," +
                "(2131230974,'The World', 'ไพ่โลก',"+"'Completion and achievement.', 'ความสำเร็จสมบูรณ์ในงาน การบรรลุเป้าหมาย',"+
                "'Celebrate your accomplishments and prepare for new challenges.','ฉลองความสำเร็จและมองหาเป้าหมายใหม่')");

        db.execSQL("CREATE TABLE tarot_money (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image_id INTEGER NOT NULL, " +
                "name TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาอังกฤษ)
                "name_th TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาไทย)
                "description TEXT NOT NULL, " + // คำอธิบาย (ภาษาอังกฤษ)
                "description_th TEXT NOT NULL, " + // คำอธิบาย (ภาษาไทย)
                "advice TEXT NOT NULL, " + // คำแนะนำ (ภาษาอังกฤษ)
                "advice_th TEXT NOT NULL)"); // คำแนะนำ (ภาษาไทย)

        db.execSQL("INSERT INTO tarot_money (image_id, name,name_th, description,description_th, advice,advice_th) VALUES " +
                "(2131230863 ,'The Fool','ไพ่โง่เขลา',"+" ' New financial opportunities with uncertainty.','โอกาสทางการเงินใหม่ การเริ่มต้นที่ไม่แน่นอน',"+
                " 'Be cautious with financial risks, but remain open to new opportunities.','ระวังความเสี่ยงด้านการเงิน แต่จงเปิดใจรับโอกาสใหม่อย่างระมัดระวัง')," +
                "(2131230897 ,'The Magician', 'ไพ่นักมายากล', "+"'Potential to earn money through personal skills.', ' มีศักยภาพในการหาเงินจากความสามารถของตนเอง',"+"" +
                "'Use your abilities and ingenuity to generate income.','ใช้ทักษะและไหวพริบในการสร้างรายได้')," +
                "(2131230864 ,'The High Priestess','ไพ่สังฆราชหญิง',"+" 'Financial uncertainty, hidden aspects.','การเงินที่ยังไม่ชัดเจน อาจมีบางสิ่งที่ยังซ่อนอยู่',"+
                " 'Trust your intuition before making financial decisions.','ใช้สัญชาตญาณก่อนตัดสินใจเกี่ยวกับเงิน')," +
                "(2131230965,'The Empress', 'ไพ่จักรพรรดินี',"+"'Abundance, income through creativity.','ความอุดมสมบูรณ์ มีรายได้จากความคิดสร้างสรรค์',"+
                " 'Invest in things that promote long-term prosperity.','ลงทุนในสิ่งที่ส่งเสริมความเจริญรุ่งเรืองในระยะยาว')," +
                "(2131230964,'The Emperor', 'ไพ่จักรพรรดิ',"+"'Financial stability, systematic money management.','ความมั่นคงทางการเงิน การจัดการเงินอย่างมีระบบ',"+
                " 'Plan finances carefully and seek secure investments.','วางแผนการเงินอย่างระมัดระวังและมองหาการลงทุนที่ปลอดภัย')," +
                "(2131230968,'The Hierophant', 'ไพ่สังฆราช',"+"'Finance involving systems, organizations, or adhering to rules.','การเงินที่เกี่ยวข้องกับระบบ องค์กร หรือการปฏิบัติตามกฎ',"+
                " 'Follow orderly methods and seek advice from financial experts.','ยึดมั่นในแนวทางที่มีระเบียบและอาศัยคำแนะนำจากผู้เชี่ยวชาญ')," +
                "(2131230969,'The Lovers', 'ไพ่คนรัก',"+"'Crucial financial decisions or collaborations.','การตัดสินใจทางการเงินที่สำคัญ หรือความร่วมมือด้านการเงิน',"+
                " 'Choose paths aligning with your goals and values.','เลือกทางที่สอดคล้องกับเป้าหมายและคุณค่าในใจ')," +
                "(2131230962,'The Chariot', 'ไพ่อัศวินรถศึก',"+"'Control and achieving financial goals.','การควบคุมและการบรรลุเป้าหมายทางการเงิน',"+
                " 'Practice discipline and strive to achieve objectives.','มีวินัยและใช้ความพยายามเพื่อบรรลุเป้าหมาย')," +
                "(2131230959,'Strength', 'ไพ่พลัง',"+"'Patience and mindful money management.','ความอดทนและการจัดการเงินอย่างมีสติ',"+
                " 'Exercise restraint in spending and save for bigger goals.','ควบคุมอารมณ์ในการใช้เงินและอดออมเพื่อเป้าหมายที่ใหญ่กว่า')," +
                "(2131230967,'The Hermit', 'ไพ่ฤๅษี',"+"'A time to save or deeply reconsider finances.','ช่วงเวลาที่ต้องประหยัดหรือพิจารณาการเงินอย่างลึกซึ้ง',"+
                " 'Find answers within about financial usage and future plans.','มองหาคำตอบในตัวเองเกี่ยวกับการใช้เงินและแผนการในอนาคต')," +
                "(2131230977,'Wheel of Fortune','ไพ่กงล้อโชคชะตา',"+" 'Unexpected financial changes, good or bad luck.','การเปลี่ยนแปลงทางการเงินที่ไม่คาดคิด โชคดีหรือโชคร้าย',"+
                " 'Stay prepared for changes and seize opportunities.',' เตรียมพร้อมรับมือกับความเปลี่ยนแปลงและใช้โอกาสเมื่อมาถึง')," +
                "(2131230885 ,'Justice','ไพ่ความยุติธรรม',"+"'Logical financial decisions or matters involving legalities.','การตัดสินใจทางการเงินที่สมเหตุสมผลหรือเกี่ยวข้องกับกฎหมาย',"+
                " 'Be thorough and verify financial documents.','พิจารณาให้รอบคอบและตรวจสอบเอกสารทางการเงิน')," +
                "(2131230966,'The Hanged Man','ไพ่คนห้อยหัว',"+" 'Financial stagnation or sacrificing for the future.','การเงินที่ชะงักงัน หรือการเสียสละบางอย่างเพื่ออนาคต', "+
                "' Be patient and rethink plans from new perspectives.','อดทนและมองสถานการณ์จากมุมใหม่เพื่อวางแผนการเงิน')," +
                "(2131230857 ,'Death', 'ไพ่แห่งความตาย',"+"'Complete financial change, bankruptcy, or new beginnings.','การเปลี่ยนแปลงด้านการเงินอย่างสิ้นเชิง เช่น การล้มละลายหรือการเริ่มต้นใหม่',"+
                " 'Let go of the old to embrace new opportunities.','ปล่อยวางสิ่งเก่าเพื่อเปิดรับโอกาสใหม่')," +
                "(2131230960,'Temperance', 'ไพ่ทางสายกลาง',"+"'Balance in finances, proper management.','การเงินที่ต้องการความสมดุลและการจัดการอย่างเหมาะสม',"+
                " 'Spend wisely and allocate resources for stability.','ใช้จ่ายอย่างมีสติและจัดสรรเงินเพื่อความมั่นคง')," +
                "(2131230963,'The Devil', 'ไพ่ปีศาจ',"+"'Financial traps like debts or excessive spending.','การติดกับดักทางการเงิน เช่น หนี้สินหรือการใช้จ่ายฟุ่มเฟือย',"+
                " 'Be wary of overindulgence and avoid unnecessary commitments.','ระวังการใช้เงินที่เกินตัวและหลีกเลี่ยงข้อผูกมัดที่ไม่จำเป็น')," +
                "(2131230973,'The Tower', 'ไพ่หอคอยถล่ม',"+"'Sudden financial loss or unforeseen problems.','การสูญเสียเงินอย่างฉับพลันหรือปัญหาทางการเงินที่ไม่คาดคิด',"+
                " 'Stay calm and focus on recovery plans.',' เตรียมพร้อมรับมือกับเหตุการณ์ที่คาดไม่ถึงและอย่าตื่นตระหนก')," +
                "(2131230971,'The Star', 'ไพ่ดวงดาว',"+"'Hope or good financial opportunities.','ความหวังหรือโอกาสทางการเงินที่ดี',"+
                " 'Stay optimistic and seize earning chances.','มีความหวังในอนาคตและใช้โอกาสในการสร้างรายได้')," +
                "(2131230970,'The Moon', 'ไพ่พระจันทร์',"+"'Financial uncertainty or unclear conditions.','ความไม่แน่นอนหรือการเงินที่ยังคลุมเครือ',"+
                " 'Check details thoroughly and avoid hasty decisions.','ตรวจสอบรายละเอียดและอย่ารีบเร่งในการตัดสินใจ')," +
                "(2131230972,'The Sun', 'ไพ่พระอาทิตย์',"+"'Financial success, increasing income.','ความสำเร็จทางการเงิน รายได้ที่เพิ่มขึ้น',"+
                " 'Use this moment to build long-term financial security.','ใช้โอกาสที่ดีนี้เพื่อสร้างความมั่นคงในระยะยาว')," +
                "(2131230884,'Judgement', 'ไพ่พิพากษา',"+"'Reviewing finances or crucial decisions with lasting impacts.','การประเมินการเงินหรือการตัดสินใจสำคัญที่ส่งผลระยะยาว',"+
                " 'Learn from the past and improve financial management.','ทบทวนและเรียนรู้จากอดีตเพื่อปรับปรุงการเงิน')," +
                "(2131230974,'The World', 'ไพ่โลก',"+"'Complete financial success, achieving goals.','ความสำเร็จทางการเงินที่สมบูรณ์ การบรรลุเป้าหมายทางการเงิน',"+
                " 'Complete financial success, achieving goals.','ฉลองความสำเร็จและวางแผนเพื่อเป้าหมายในอนาคต')");


        db.execSQL("CREATE TABLE tarot_love (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image_id INTEGER NOT NULL, " +
                "name TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาอังกฤษ)
                "name_th TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาไทย)
                "description TEXT NOT NULL, " + // คำอธิบาย (ภาษาอังกฤษ)
                "description_th TEXT NOT NULL, " + // คำอธิบาย (ภาษาไทย)
                "advice TEXT NOT NULL, " + // คำแนะนำ (ภาษาอังกฤษ)
                "advice_th TEXT NOT NULL)"); // คำแนะนำ (ภาษาไทย)

        db.execSQL("INSERT INTO tarot_love (image_id, name,name_th, description,description_th, advice,advice_th) VALUES " +
                "(2131230863 ,'The Fool','ไพ่โง่เขลา',"+" ' New beginnings, spontaneous love, excitement, and adventure','การเริ่มต้นใหม่ ความรักที่ไร้กฎเกณฑ์ ความตื่นเต้นและการผจญภัย',"+
                " 'Keep an open heart for new love and take risks, but don’t be overly naive.','เปิดใจให้กับความรักใหม่ ๆ และอย่ากลัวที่จะลองเสี่ยง แต่ระวังอย่าไร้เดียงสาจนเกินไป')," +
                "(2131230897 ,'The Magician','ไพ่นักมายากล',"+" 'Effective communication, potential in a relationship, strong attraction.','การสื่อสารที่ดี ความสัมพันธ์ที่มีศักยภาพ การดึงดูดที่รุนแรง',"+
                " 'Use your charm and skills to build a meaningful connection.','ใช้เสน่ห์และความสามารถในการสร้างความสัมพันธ์ที่มีความหมาย')," +
                "(2131230864 ,'The High Priestess','ไพ่สังฆราชหญิง',"+" 'Mysteries in love, spiritual connection, intuition.', ' ความลึกลับในความสัมพันธ์ การเชื่อมโยงทางจิตวิญญาณ',"+
                "' Trust your instincts and pay attention to what’s hidden in the relationship.','ฟังเสียงหัวใจและสัญชาตญาณของคุณ อาจมีบางอย่างที่ซ่อนเร้นอยู่')," +
                "(2131230965,'The Empress', 'ไพ่จักรพรรดินี','+''Warm, nurturing love, a fulfilling and abundant relationship.','ความรักที่อบอุ่นและเต็มไปด้วยการดูแล ความสัมพันธ์ที่มีความสมบูรณ์',"+
                " 'Show tenderness and care in your love life.','แสดงความอ่อนโยนและให้ความใส่ใจในความรัก')," +
                "(2131230964,'The Emperor', 'ไพ่จักรพรรดิ',"+"'Stability, structure, and order in the relationship.','ความสัมพันธ์ที่มั่นคง การมีระเบียบและโครงสร้างในความรัก',"+
                " 'Balance leadership and partnership in love.','รักษาความสมดุลระหว่างความรักกับความเป็นผู้นำในชีวิตคู่')," +
                "(2131230968,'The Hierophant', 'ไพ่สังฆราช',"+"'Commitment, traditional values in relationships, marriage.','ความสัมพันธ์ที่ยึดมั่นในคุณค่าและประเพณี การแต่งงาน',"+
                " 'Focus on long-term stability and mutual support in love.','ให้ความสำคัญกับความสัมพันธ์ในระยะยาวและการสนับสนุนซึ่งกันและกัน')," +
                "(2131230969,'The Lovers', 'ไพ่คนรัก',"+"'True love, important decisions in relationships.','ความรักที่แท้จริง การตัดสินใจเกี่ยวกับความสัมพันธ์',"+
                " 'Follow your heart and stay true to your values in love.','เลือกในสิ่งที่หัวใจของคุณบอกและรักษาความซื่อสัตย์ในความรัก')," +
                "(2131230962,'The Chariot', 'ไพ่อัศวินรถศึก',"+"'Success in love through determination and control.','ความสำเร็จในความรักด้วยความมุ่งมั่นและการควบคุม',"+
                " 'Avoid letting conflict or pressure harm the relationship.','อย่าปล่อยให้ความขัดแย้งหรือแรงกดดันทำลายความสัมพันธ์')," +
                "(2131230959,'Strength', 'ไพ่พลัง',"+"' Love that requires patience and understanding.','ความรักที่ต้องใช้ความอดทนและความเข้าใจ',"+
                " ' Use compassion and acceptance to resolve challenges in the relationship.','ใช้ความเมตตาและการยอมรับเพื่อแก้ไขปัญหาในความสัมพันธ์')," +
                "(2131230967,'The Hermit','ไพ่ฤๅษี',"+" 'Searching for the meaning of love, introspection, or solitude.','การค้นหาความหมายของความรัก ความสันโดษ',"+
                " 'Take time to reflect on what you truly need in love.','ใช้เวลาทบทวนตัวเองเพื่อเรียนรู้เกี่ยวกับความรักที่คุณต้องการ')," +
                "(2131230977,'Wheel of Fortune','ไพ่กงล้อโชคชะตา',"+" 'Changes in love, destiny taking its course.','การเปลี่ยนแปลงในความรัก โชคชะตานำพา',"+
                " 'Be open to new opportunities and shifts in the relationship.','เปิดใจรับสิ่งใหม่ที่อาจเกิดขึ้นในความสัมพันธ์')," +
                "(2131230885 ,'Justice','ไพ่ความยุติธรรม',"+"'Fairness, balance, and accountability in relationships.','ความสัมพันธ์ที่ยุติธรรมและเท่าเทียม',"+
                " 'Do what’s right and maintain fairness in your love life.','ทำสิ่งที่ถูกต้องและรักษาสมดุลในความรัก')," +
                "(2131230966,'The Hanged Man','ไพ่คนห้อยหัว',"+" 'The end of a relationship, significant transformation.',' การหยุดชั่วคราวในความสัมพันธ์ การมองสิ่งต่าง ๆ จากมุมมองใหม่',"+
                " 'Accept change and embrace new beginnings.','ให้เวลาในการพิจารณาว่าความสัมพันธ์นี้ควรดำเนินไปอย่างไร')," +
                "(2131230857 ,'Death','ไพ่แห่งความตาย',"+" 'The end of a relationship, significant transformation.',' การสิ้นสุดของความสัมพันธ์เดิม การเปลี่ยนแปลงที่สำคัญ',"+
                " 'Accept change and embrace new beginnings.','ยอมรับการเปลี่ยนแปลงและเปิดใจให้กับการเริ่มต้นใหม่')," +
                "(2131230960,'Temperance','ไพ่ทางสายกลาง',"+" 'Balance, harmony, and compromise in love.','ความรักที่สมดุลและการประนีประนอม',"+
                " 'Maintain moderation and mutual understanding in the relationship.',' รักษาความพอดีในความสัมพันธ์และเรียนรู้ที่จะเข้าใจกัน')," +
                "(2131230963,'The Devil', 'ไพ่ปีศาจ',"+"' Passion, obsession, or toxic relationships.',' ความหลงใหล ความสัมพันธ์ที่ติดยึดหรือความสัมพันธ์ท้อกซิก',"+
                " 'Free yourself from unhealthy love or attachments.','ปลดปล่อยตัวเองจากความรักที่ไม่ดีต่อใจและตัวคุณ')," +
                "(2131230973,'The Tower', 'ไพ่หอคอยถล่ม',"+"'Sudden changes in a relationship, upheaval..','การเปลี่ยนแปลงแบบกะทันหันในความสัมพันธ์',"+
                " 'Don’t fear the truth; accept and adapt to the transformation.','อย่ากลัวการเผชิญหน้ากับความจริงและยอมรับความเปลี่ยนแปลง')," +
                "(2131230971,'The Star','ไพ่ดวงดาว',"+" 'New hope and healing in love.','ความหวังใหม่และการฟื้นตัวในความรัก',"+
                " 'Be optimistic and open to bright, positive love.','เปิดใจให้กับความรักที่สดใสและเต็มไปด้วยความหวัง')," +
                "(2131230970,'The Moon','ไพ่พระจันทร์',"+" 'Uncertainty, secrets, and illusions in love.','ความไม่แน่นอนและความลับในความรัก',"+
                " ' Seek clarity and trust your instincts to avoid confusion.','ระวังความสับสนในความสัมพันธ์และหาความชัดเจน')," +
                "(2131230972,'The Sun', 'ไพ่พระอาทิตย์',"+"'Happiness, openness, and joyful love.','ความสุข ความรักที่สดใสและเปิดเผย',"+
                " ' Enjoy the present moment and celebrate the love you have.','สนุกกับความรักและความสุขที่คุณมีในปัจจุบัน')," +
                "(2131230884,'Judgement','ไพ่พิพากษา',"+" 'Reassessing the relationship, starting anew.','การประเมินความสัมพันธ์และการเริ่มต้นใหม่',"+
                " 'Learn from past experiences to build a better future in love.','ใช้บทเรียนจากอดีตเพื่อพัฒนาความสัมพันธ์ในอนาคต')," +
                "(2131230974,'The World','ไพ่โลก',"+" 'Fulfillment, completion, and harmony in love.', 'ความสัมพันธ์ที่สมบูรณ์และเติมเต็ม',"+
                "'Celebrate your achievements in love and set new goals together.','เฉลิมฉลองความสำเร็จในความรักและตั้งเป้าหมายใหม่')");




        db.execSQL("CREATE TABLE tarot_health (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image_id INTEGER NOT NULL, " +
                "name TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาอังกฤษ)
                "name_th TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาไทย)
                "description TEXT NOT NULL, " + // คำอธิบาย (ภาษาอังกฤษ)
                "description_th TEXT NOT NULL, " + // คำอธิบาย (ภาษาไทย)
                "advice TEXT NOT NULL, " + // คำแนะนำ (ภาษาอังกฤษ)
                "advice_th TEXT NOT NULL)"); // คำแนะนำ (ภาษาไทย)

        db.execSQL("INSERT INTO tarot_health (image_id, name,name_th, description,description_th, advice,advice_th) VALUES " +
                "(2131230863 ,'The Fool','ไพ่โง่เขลา',"+
                " 'New energy, refreshed beginnings.','พลังงานใหม่ การเริ่มต้นที่สดชื่น',"+
                " 'Focus on basic health and avoid minor accidents from carelessness.',' ดูแลสุขภาพพื้นฐานและระวังอุบัติเหตุเล็กๆ น้อยๆ ที่อาจเกิดจากความประมาท')," +
                "(2131230897 ,'The Magician','ไพ่นักมายากล',"+
                " 'Physical and mental balance.','ความสมดุลของร่างกายและจิตใจ',"+
                " 'Use energy creatively and consider holistic healing approaches.','ใช้พลังงานอย่างสร้างสรรค์ พิจารณาการบำบัดที่ผสมผสานทั้งร่างกายและจิตใจ')," +
                "(2131230864 ,'The High Priestess','ไพ่สังฆราชหญิง',"+
                " 'Hidden health issues.','สุขภาพที่อาจมีบางสิ่งซ่อนเร้น เช่น ปัญหาที่มองไม่เห็น',"+
                " 'Listen to your body and get regular checkups.','ฟังเสียงจากภายในร่างกายและตรวจสุขภาพเป็นระยะ')," +
                "(2131230965,'The Empress','ไพ่จักรพรรดินี',"+
                " 'Robust health, physical vitality, fertility.','สุขภาพที่ดี อุดมสมบูรณ์ โดยเฉพาะด้านการเจริญพันธุ์',"+
                " 'Focus on nurturing your body through proper nutrition and self-care.','ดูแลตัวเองด้วยอาหารที่ดีและพักผ่อนให้เพียงพอ')," +
                "(2131230964,'The Emperor','ไพ่จักรพรรดิ',"+
                " 'Stability in health, building strength.','ความแข็งแกร่งและสุขภาพที่มั่นคง',"+
                " 'Maintain discipline in health routines and strengthen your body with consistent habits.','ให้ความสำคัญกับการวางแผนออกกำลังกายและการดูแลตนเองในระยะยาว')," +
                "(2131230968,'The Hierophant','ไพ่สังฆราช',"+
                " 'Traditional approaches to health or healing.','การปฏิบัติตามคำแนะนำจากผู้เชี่ยวชาญ เช่น แพทย์หรือโค้ชสุขภาพ',"+
                " 'Follow trusted methods or consult experienced professionals.',' ยึดมั่นในระเบียบวินัยที่ดีต่อสุขภาพ')," +
                "(2131230969,'The Lovers','ไพ่คนรัก',"+
                " 'Balance and harmony in emotional and physical well-being.','ความสมดุลของพลังงานทางกายและใจ',"+
                " 'Address emotional health and relationships to improve overall wellness.','ให้ความสำคัญกับความสัมพันธ์ที่ส่งผลดีต่อสุขภาพจิต')," +
                "(2131230962,'The Chariot','ไพ่อัศวินรถศึก',"+
                " 'Strong willpower in overcoming health challenges.','สุขภาพที่สามารถควบคุมได้ด้วยความมุ่งมั่น',"+
                " 'Stay determined and committed to your health goals.','ฝึกวินัยและตั้งเป้าหมายในเรื่องสุขภาพ เช่น การออกกำลังกาย')," +
                "(2131230959,'Strength','ไพ่พลัง',"+
                " 'Physical strength and emotional resilience.','พลังภายในและการฟื้นตัวที่ดี',"+
                " 'Cultivate inner strength and manage stress effectively.','ใช้ความอดทนและความแข็งแกร่งในการดูแลสุขภาพ')," +
                "(2131230967,'The Hermit','ไพ่ฤๅษี',"+
                " 'Solitude and reflection on health matters.','ความจำเป็นต้องพักผ่อนหรือทบทวนสุขภาพของตนเอง',"+
                " 'Take time to understand and address underlying health concerns.','ใช้เวลาส่วนตัวเพื่อฟื้นฟูทั้งกายและใจ')," +
                "(2131230977,'Wheel of Fortune','ไพ่กงล้อโชคชะตา',"+
                " 'Fluctuations in health, unexpected changes.','การเปลี่ยนแปลงทางสุขภาพ อาจดีขึ้นหรือแย่ลง',"+
                " 'Adapt to new circumstances and seek proper care if issues arise.','เตรียมพร้อมรับมือกับการเปลี่ยนแปลง และดูแลตัวเองอย่างต่อเนื่อง')," +
                "(2131230885 ,'Justice','ไพ่ความยุติธรรม',"+
                "' Balance and fairness in health.','สุขภาพที่ขึ้นอยู่กับการตัดสินใจและความสมดุล',"+
                " ' Focus on moderation and ensure fairness in your self-care practices.','รับผิดชอบต่อการดูแลตนเอง เช่น การทานอาหารหรือการออกกำลังกาย')," +
                "(2131230966,'The Hanged Man','ไพ่คนห้อยหัว',"+
                " 'Temporary health setbacks or delays in recovery.','สุขภาพอาจมีการชะลอหรือหยุดชะงัก เช่น ต้องพักฟื้น',"+
                " 'Be patient with healing and consider new perspectives or alternative treatments.','ยอมรับสถานการณ์และให้เวลาในการฟื้นตัว')," +
                "(2131230857 ,'Death', 'ไพ่แห่งความตาย',"+
                "'End of old health habits, transformation.','การเปลี่ยนแปลงด้านสุขภาพ เช่น การปล่อยวางนิสัยที่ไม่ดี',"+
                " 'Let go of unhealthy patterns and embrace a healthier lifestyle.','เริ่มต้นวิถีชีวิตใหม่เพื่อสุขภาพที่ดีขึ้น')," +
                "(2131230960,'Temperance','ไพ่ทางสายกลาง',"+
                " 'Balance and moderation in health.','การสร้างสมดุลด้านสุขภาพ เช่น อาหาร การนอน การออกกำลังกาย',"+
                " 'Avoid extremes and maintain a balanced approach to diet and exercise.','จัดการชีวิตให้อยู่ในจุดที่พอ')," +
                "(2131230963,'The Devil','ไพ่ปีศาจ',"+
                " 'Overindulgence or unhealthy habits.',' การพึ่งพาสิ่งที่ไม่ดีต่อสุขภาพ เช่น การติดอาหารหรือสารเสพติด',"+
                " 'Break free from addictions or harmful behaviors.','ตระหนักถึงสิ่งที่ทำร้ายสุขภาพและปลดปล่อยตัวเองจากพันธนาการนั้น')," +
                "(2131230973,'The Tower','ไพ่หอคอยถล่ม',"+
                " 'Sudden health issues or disruptions.',' ปัญหาสุขภาพที่เกิดขึ้นอย่างฉับพลันหรือการบาดเจ็บ',"+
                " 'Stay resilient and seek immediate professional help if needed.','อย่าตื่นตระหนกและให้ความสำคัญกับการฟื้นตัว')," +
                "(2131230971,'The Star','ไพ่ดวงดาว',"+
                " 'Recovery and renewed vitality.','การฟื้นฟูและความหวังในด้านสุขภาพ',"+
                " 'Focus on healing and restoring optimism in your health journey.','ห้ความสำคัญกับการบำบัดและการรักษาที่ช่วยเสริมสร้างพลังงานใหม่')," +
                "(2131230970,'The Moon', 'ไพ่พระจันทร์',"+
                "'Uncertainty or hidden health issues.','ความสับสนในสุขภาพ หรืออาการที่ไม่สามารถวินิจฉัยได้ชัดเจน' ,"+
                "'Pay close attention to symptoms and seek clarity through proper diagnoses.','ตรวจสอบให้ละเอียดและฟังเสียงจากร่างกายของคุณ')," +
                "(2131230972,'The Sun','ไพ่พระอาทิตย์',"+
                " 'Vitality, energy, and good health.','สุขภาพที่ดีและมีพลังงานที่สดใส',"+
                " 'Enjoy this period of well-being but continue maintaining healthy habits.','ใช้ช่วงเวลานี้เพื่อเสริมสร้างสุขภาพในทางที่ดี')," +
                "(2131230884,'Judgement','ไพ่พิพากษา',"+
                " 'Health assessment and recovery.','การตัดสินใจเกี่ยวกับการเปลี่ยนแปลงสุขภาพ เช่น การเริ่มดูแลตัวเอง',"+
                " 'Reflect on past health choices and embrace change for better well-being.','ทบทวนและลงมือทำเพื่อสุขภาพที่ดีกว่า')," +
                "(2131230974,'The World','',"+
                " 'Completion of a health journey, overall wellness.','สุขภาพที่สมบูรณ์และความสมดุล',"+
                " 'Celebrate your achievements in health and continue striving for balance.','ฉลองความสำเร็จในการดูแลสุขภาพ และรักษามาตรฐานนี้ต่อไป')");



        db.execSQL("CREATE TABLE tarot_luck (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image_id INTEGER NOT NULL, " +
                "name TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาอังกฤษ)
                "name_th TEXT NOT NULL, " + // ชื่อไพ่ (ภาษาไทย)
                "description TEXT NOT NULL, " + // คำอธิบาย (ภาษาอังกฤษ)
                "description_th TEXT NOT NULL, " + // คำอธิบาย (ภาษาไทย)
                "advice TEXT NOT NULL, " + // คำแนะนำ (ภาษาอังกฤษ)
                "advice_th TEXT NOT NULL)"); // คำแนะนำ (ภาษาไทย)

        db.execSQL("INSERT INTO tarot_luck (image_id, name,name_th, description,description_th, advice,advice_th) VALUES " +
                "(2131230863 ,'The Fool','ไพ่โง่เขลา',"+
                "'New opportunities or unexpected luck. Taking risks might bring good results, but beware of carelessness.','โอกาสใหม่ๆ หรือการเริ่มต้นที่อาจนำไปสู่โชคลาภแบบไม่คาดคิด การเสี่ยงโชคอาจได้ผลดี แต่ต้องระวังความประมาท',"+
                " 'Invest in new ventures, but proceed with awareness and foresight.',' ลงทุนในสิ่งใหม่ๆ ได้ แต่ต้องมีสติและมองการณ์ไกล')," +
                "(2131230897 ,'The Magician', 'ไพ่นักมายากล',"+
                "'Fortune through personal skills, creativity, and negotiation.','โอกาสในการสร้างรายได้ด้วยความสามารถของตนเอง การเจรจาและความคิดสร้างสรรค์จะช่วยเสริมโชคลาภ',"+
                " 'Use your talents to create opportunities.','ใช้ทักษะที่คุณมีเพื่อเปิดโอกาสให้ตนเอง')," +
                "(2131230864 ,'The High Priestess','ไพ่สังฆราชหญิง',"+
                " 'Luck from intuition or hidden knowledge. It may involve waiting for the right moment.','โชคลาภที่มาจากการฟังสัญชาตญาณหรือความรู้ภายใน อาจเกี่ยวข้องกับการรอจังหวะที่เหมาะสม',"+
                " ' Trust your inner voice or uncover concealed information.','ฟังเสียงในใจหรือข้อมูลลับที่ยังไม่เปิดเผย')," +
                "(2131230965,'The Empress','ไพ่จักรพรรดิน',"+
                " 'Abundance, prosperity related to love, family, or creativity.','ความอุดมสมบูรณ์ โชคลาภที่เกี่ยวข้องกับความรัก ครอบครัว หรือการสร้างสรรค์สิ่งใหม่',"+
                " 'Invest in beauty, art, or property for long-term gains.','ลงทุนในสิ่งที่เกี่ยวกับความงามหรือทรัพย์สิน เช่น ที่ดินหรือศิลปะ')," +
                "(2131230964,'The Emperor','ไพ่จักรพรรดิ',"+
                " 'Financial stability and fortune through planning and order.','ความมั่นคงด้านการเงินและทรัพย์สิน โชคลาภที่มาจากการจัดการที่มีระเบียบและการวางแผน',"+
                " 'Focus on structured, secure investments or businesses.','ทำธุรกิจหรือการลงทุนที่มั่นคงและมีโครงสร้าง')," +
                "(2131230968,'The Hierophant','ไพ่สังฆราช',"+
                " 'Luck through traditional values or guidance from experienced individuals.','ชคลาภที่มาจากความเชื่อดั้งเดิม หรือการได้รับคำแนะนำจากผู้ใหญ่',"+
                " 'Trust in rules and advice from those with wisdom.','เชื่อในกฎเกณฑ์และความรู้จากคนที่มีประสบการณ์')," +
                "(2131230969,'The Lovers','ไพ่คนรัก',"+
                " 'Fortune through partnerships or key decisions.','โชคลาภที่มาจากการร่วมมือกับผู้อื่น หรือการตัดสินใจที่สำคัญ',"+
                " 'Collaborate with others and make thoughtful choices.','ใช้โอกาสที่เกี่ยวข้องกับหุ้นส่วนหรือความสัมพันธ์')," +
                "(2131230962,'The Chariot','ไพ่อัศวินรถศึก',"+
                " 'Luck gained through determination and effort; possible victory in competitive ventures.','โชคลาภที่มาจากความมุ่งมั่นและความพยายาม อาจหมายถึงชัยชนะในการเดิมพัน',"+
                " 'Move forward confidently but maintain emotional control.','เดินหน้าอย่างมั่นใจ แต่ต้องควบคุมอารมณ์')," +
                "(2131230959,'Strength','ไพ่พลัง',"+
                "'Fortune from patience and self-control.','โชคลาภที่มาจากความอดทนและการควบคุมตนเอง',"+
                " 'Stay disciplined and persistent; good things will come your way.','อดทนและมีวินัยในสิ่งที่ทำอยู่ โชคลาภจะมาถึง')," +
                "(2131230967,'The Hermit','ไพ่ฤๅษี',"+
                " 'Luck derived from self-reflection or discovering new directions.','โชคลาภที่มาจากความเข้าใจในตัวเองและการค้นพบแนวทางใหม่ๆ',"+
                " 'Take time to research or contemplate before acting.','ใช้เวลาไตร่ตรองหรือค้นคว้าก่อนตัดสินใจลงทุน')," +
                "(2131230977,'Wheel of Fortune','ไพ่กงล้อโชคชะตา',"+
                " 'Unexpected changes in fortune; luck may turn in your favor.','โชคลาภที่พลิกผัน อาจหมายถึงการได้รับโชคโดยไม่คาดคิด',"+
                " 'Be prepared to seize opportunities when they arise.','เตรียมพร้อมรับโอกาสเมื่อวงล้อหมุนมาในทางที่ดี')," +
                "(2131230885 ,'Justice','ไพ่ความยุติธรรม',"+
                "'Fortune through fair decisions or receiving what you deserve.','โชคลาภที่มาจากการตัดสินใจที่ยุติธรรม หรือการได้รับสิ่งที่สมควรได้รับ',"+
                " 'Do what is right, and luck will follow.','ทำสิ่งที่ถูกต้อง โชคลาภจะมาหา')," +
                "(2131230966,'The Hanged Man','ไพ่คนห้อยหัว',"+
                " 'Luck that requires patience or comes from a different perspective.','โชคลาภที่ต้องรอเวลา หรือมาจากมุมมองที่แตกต่าง',"+
                " 'Avoid rushing; think carefully before acting.','อย่าเร่งรีบ คิดให้รอบคอบก่อนลงมือทำ')," +
                "(2131230857 ,'Death','ไพ่แห่งความตาย',"+
                " 'Fortune through transformation and letting go of the past.','โชคลาภที่มาจากการเปลี่ยนแปลงและการละทิ้งสิ่งเก่า',"+
                " 'Embrace change to create new beginnings.','กล้าเปลี่ยนแปลงเพื่อเริ่มต้นใหม่')," +
                "(2131230960,'Temperance','ไพ่ทางสายกลาง',"+
                " 'Luck through balance and adaptability.','โชคลาภที่มาจากความสมดุลและการปรับตัว',"+
                " ' Invest carefully and take things one step at a time.',' ลงทุนอย่างระมัดระวังและค่อยเป็นค่อยไป')," +
                "(2131230963,'The Devil','ไพ่ปีศาจ',"+
                " 'Fortune through risk-taking or desire, but beware of consequences.','โชคลาภที่มาจากการเสี่ยงหรือความโลภ แต่ต้องระวังผลเสีย',"+
                " 'Don’t be tempted by short-term gains.','อย่าถูกล่อลวงโดยผลประโยชน์ระยะสั้น')," +
                "(2131230973,'The Tower','ไพ่หอคอยถล่ม',"+
                " 'Loss that leads to new fortune, or sudden, unexpected luck.','การสูญเสียที่นำไปสู่โชคลาภใหม่ หรือโชคที่เกิดขึ้นอย่างกะทันหัน',"+
                " 'Embrace major changes without fear.','การสูญเสียที่นำไปสู่โชคลาภใหม่ หรือโชคที่เกิดขึ้นอย่างกะทันหัน')," +
                "(2131230971,'The Star','ไพ่ดวงดาว' ,"+
                "' Luck from hope and inspiration.','ชคลาภที่มาจากความหวังและแรงบันดาลใจ',"+
                " 'Trust in yourself and look for sustainable opportunities.','เชื่อมั่นในตัวเองและมองหาโอกาสที่ยั่งยืน')," +
                "(2131230970,'The Moon','ไพ่พระจันทร์',"+
                " 'Luck associated with mystery or uncertain situations.','โชคลาภที่เกี่ยวข้องกับสิ่งลึกลับหรือการคาดเดาที่ไม่แน่นอน',"+
                " 'Be cautious of unclear situations; verify information before acting.','ระวังความไม่ชัดเจนและตรวจสอบข้อมูลก่อนตัดสินใจ')," +
                "(2131230972,'The Sun','ไพ่พระอาทิตย์',"+
                " ' Fortune tied to success, happiness, and positivity.','โชคลาภที่เกี่ยวข้องกับความสำเร็จและความสุข',"+
                " 'Invest in creative and uplifting pursuits.','ลงทุนในสิ่งที่สร้างสรรค์และเป็นบวก')," +
                "(2131230884,'Judgement','ไพ่พิพากษา',"+
                " 'Luck from new opportunities or decisive actions.','โชคลาภที่มาจากโอกาสครั้งใหม่ หรือการตัดสินใจที่เด็ดขาด',"+
                " 'Learn from the past and begin anew with confidence.','ยอมรับบทเรียนในอดีตและเริ่มต้นใหม่อย่างมั่นใจ')," +
                "(2131230974,'The World','ไพ่โลก',"+
                " 'Luck from fulfillment and achieving goals.','โชคลาภที่มาจากความสำเร็จสมบูรณ์หรือการบรรลุเป้าหมาย',"+
                " 'Invest in ventures involving travel or global expansion.','ลงทุนในสิ่งที่เกี่ยวข้องกับต่างประเทศหรือการขยายตัว')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Database", "Upgrading database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        db.execSQL("DROP TABLE IF EXISTS tarot_money"); // ลบตารางอื่นๆ ด้วย (ถ้ามี)
        db.execSQL("DROP TABLE IF EXISTS tarot_love"); // ลบตารางอื่นๆ ด้วย (ถ้ามี)
        db.execSQL("DROP TABLE IF EXISTS tarot_health"); // ลบตารางอื่นๆ ด้วย (ถ้ามี)
        db.execSQL("DROP TABLE IF EXISTS tarot_luck"); // ลบตารางอื่นๆ ด้วย (ถ้ามี)
        onCreate(db);
    }


    // เพิ่มฟังก์ชัน getCardByImageId ลงในคลาสนี้
    public List<Map<String, String>> getCardByImageId(int selectedCard, String language) {
        List<Map<String, String>> tarotCards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Proper concatenation of the SQL query
        String query = "SELECT id, image_id, name, name_th, description, description_th, advice, advice_th " +
                "FROM tarot_career WHERE image_id = ?"; // Using ? for parameterized query

        // Query execution
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(selectedCard)});

        Log.d("Debug", "Cursor Columns: " + Arrays.toString(cursor.getColumnNames()));

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> card = new HashMap<>();

                // Get column indices
                int idIndex = cursor.getColumnIndex("id");
                if (idIndex != -1) card.put("id", cursor.getString(idIndex));

                int imageIdIndex = cursor.getColumnIndex("image_id");
                if (imageIdIndex != -1) card.put("image_id", cursor.getString(imageIdIndex));

                // Check language and retrieve the corresponding values
                if ("th".equals(language)) {
                    int nameThIndex = cursor.getColumnIndex("name_th");
                    if (nameThIndex != -1) card.put("name", cursor.getString(nameThIndex));

                    int descriptionThIndex = cursor.getColumnIndex("description_th");
                    if (descriptionThIndex != -1) card.put("description", cursor.getString(descriptionThIndex));

                    int adviceThIndex = cursor.getColumnIndex("advice_th");
                    if (adviceThIndex != -1) card.put("advice", cursor.getString(adviceThIndex));
                } else {
                    int nameIndex = cursor.getColumnIndex("name");
                    if (nameIndex != -1) card.put("name", cursor.getString(nameIndex));

                    int descriptionIndex = cursor.getColumnIndex("description");
                    if (descriptionIndex != -1) card.put("description", cursor.getString(descriptionIndex));

                    int adviceIndex = cursor.getColumnIndex("advice");
                    if (adviceIndex != -1) card.put("advice", cursor.getString(adviceIndex));
                }

                tarotCards.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return tarotCards;
    }


    public List<Map<String, String>> getCardByImageIdMoney(int selectedCard, String language) {
        List<Map<String, String>> tarotCards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Proper concatenation of the SQL query
        String query = "SELECT id, image_id, name, name_th, description, description_th, advice, advice_th " +
                "FROM tarot_money WHERE image_id = ?"; // Using ? for parameterized query

        // Query execution
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(selectedCard)});

        Log.d("Debug", "Cursor Columns: " + Arrays.toString(cursor.getColumnNames()));

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> card = new HashMap<>();

                // Get column indices
                int idIndex = cursor.getColumnIndex("id");
                if (idIndex != -1) card.put("id", cursor.getString(idIndex));

                int imageIdIndex = cursor.getColumnIndex("image_id");
                if (imageIdIndex != -1) card.put("image_id", cursor.getString(imageIdIndex));

                // Check language and retrieve the corresponding values
                if ("th".equals(language)) {
                    int nameThIndex = cursor.getColumnIndex("name_th");
                    if (nameThIndex != -1) card.put("name", cursor.getString(nameThIndex));

                    int descriptionThIndex = cursor.getColumnIndex("description_th");
                    if (descriptionThIndex != -1) card.put("description", cursor.getString(descriptionThIndex));

                    int adviceThIndex = cursor.getColumnIndex("advice_th");
                    if (adviceThIndex != -1) card.put("advice", cursor.getString(adviceThIndex));
                } else {
                    int nameIndex = cursor.getColumnIndex("name");
                    if (nameIndex != -1) card.put("name", cursor.getString(nameIndex));

                    int descriptionIndex = cursor.getColumnIndex("description");
                    if (descriptionIndex != -1) card.put("description", cursor.getString(descriptionIndex));

                    int adviceIndex = cursor.getColumnIndex("advice");
                    if (adviceIndex != -1) card.put("advice", cursor.getString(adviceIndex));
                }

                tarotCards.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return tarotCards;
    }

    public List<Map<String, String>> getCardByImageIdLove(int selectedCard, String language) {
        List<Map<String, String>> tarotCards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Proper concatenation of the SQL query
        String query = "SELECT id, image_id, name, name_th, description, description_th, advice, advice_th " +
                "FROM tarot_love WHERE image_id = ?"; // Using ? for parameterized query

        // Query execution
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(selectedCard)});

        Log.d("Debug", "Cursor Columns: " + Arrays.toString(cursor.getColumnNames()));

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> card = new HashMap<>();

                // Get column indices
                int idIndex = cursor.getColumnIndex("id");
                if (idIndex != -1) card.put("id", cursor.getString(idIndex));

                int imageIdIndex = cursor.getColumnIndex("image_id");
                if (imageIdIndex != -1) card.put("image_id", cursor.getString(imageIdIndex));

                // Check language and retrieve the corresponding values
                if ("th".equals(language)) {
                    int nameThIndex = cursor.getColumnIndex("name_th");
                    if (nameThIndex != -1) card.put("name", cursor.getString(nameThIndex));

                    int descriptionThIndex = cursor.getColumnIndex("description_th");
                    if (descriptionThIndex != -1) card.put("description", cursor.getString(descriptionThIndex));

                    int adviceThIndex = cursor.getColumnIndex("advice_th");
                    if (adviceThIndex != -1) card.put("advice", cursor.getString(adviceThIndex));
                } else {
                    int nameIndex = cursor.getColumnIndex("name");
                    if (nameIndex != -1) card.put("name", cursor.getString(nameIndex));

                    int descriptionIndex = cursor.getColumnIndex("description");
                    if (descriptionIndex != -1) card.put("description", cursor.getString(descriptionIndex));

                    int adviceIndex = cursor.getColumnIndex("advice");
                    if (adviceIndex != -1) card.put("advice", cursor.getString(adviceIndex));
                }

                tarotCards.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return tarotCards;
    }
    public List<Map<String, String>> getCardByImageIdHealth(int selectedCard, String language) {
        List<Map<String, String>> tarotCards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Proper concatenation of the SQL query
        String query = "SELECT id, image_id, name, name_th, description, description_th, advice, advice_th " +
                "FROM tarot_health WHERE image_id = ?"; // Using ? for parameterized query

        // Query execution
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(selectedCard)});

        Log.d("Debug", "Cursor Columns: " + Arrays.toString(cursor.getColumnNames()));

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> card = new HashMap<>();

                // Get column indices
                int idIndex = cursor.getColumnIndex("id");
                if (idIndex != -1) card.put("id", cursor.getString(idIndex));

                int imageIdIndex = cursor.getColumnIndex("image_id");
                if (imageIdIndex != -1) card.put("image_id", cursor.getString(imageIdIndex));

                // Check language and retrieve the corresponding values
                if ("th".equals(language)) {
                    int nameThIndex = cursor.getColumnIndex("name_th");
                    if (nameThIndex != -1) card.put("name", cursor.getString(nameThIndex));

                    int descriptionThIndex = cursor.getColumnIndex("description_th");
                    if (descriptionThIndex != -1) card.put("description", cursor.getString(descriptionThIndex));

                    int adviceThIndex = cursor.getColumnIndex("advice_th");
                    if (adviceThIndex != -1) card.put("advice", cursor.getString(adviceThIndex));
                } else {
                    int nameIndex = cursor.getColumnIndex("name");
                    if (nameIndex != -1) card.put("name", cursor.getString(nameIndex));

                    int descriptionIndex = cursor.getColumnIndex("description");
                    if (descriptionIndex != -1) card.put("description", cursor.getString(descriptionIndex));

                    int adviceIndex = cursor.getColumnIndex("advice");
                    if (adviceIndex != -1) card.put("advice", cursor.getString(adviceIndex));
                }

                tarotCards.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return tarotCards;
    }
    public List<Map<String, String>> getCardByImageIdLuck(int selectedCard, String language) {
        List<Map<String, String>> tarotCards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Proper concatenation of the SQL query
        String query = "SELECT id, image_id, name, name_th, description, description_th, advice, advice_th " +
                "FROM tarot_luck WHERE image_id = ?"; // Using ? for parameterized query

        // Query execution
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(selectedCard)});

        Log.d("Debug", "Cursor Columns: " + Arrays.toString(cursor.getColumnNames()));

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> card = new HashMap<>();

                // Get column indices
                int idIndex = cursor.getColumnIndex("id");
                if (idIndex != -1) card.put("id", cursor.getString(idIndex));

                int imageIdIndex = cursor.getColumnIndex("image_id");
                if (imageIdIndex != -1) card.put("image_id", cursor.getString(imageIdIndex));

                // Check language and retrieve the corresponding values
                if ("th".equals(language)) {
                    int nameThIndex = cursor.getColumnIndex("name_th");
                    if (nameThIndex != -1) card.put("name", cursor.getString(nameThIndex));

                    int descriptionThIndex = cursor.getColumnIndex("description_th");
                    if (descriptionThIndex != -1) card.put("description", cursor.getString(descriptionThIndex));

                    int adviceThIndex = cursor.getColumnIndex("advice_th");
                    if (adviceThIndex != -1) card.put("advice", cursor.getString(adviceThIndex));
                } else {
                    int nameIndex = cursor.getColumnIndex("name");
                    if (nameIndex != -1) card.put("name", cursor.getString(nameIndex));

                    int descriptionIndex = cursor.getColumnIndex("description");
                    if (descriptionIndex != -1) card.put("description", cursor.getString(descriptionIndex));

                    int adviceIndex = cursor.getColumnIndex("advice");
                    if (adviceIndex != -1) card.put("advice", cursor.getString(adviceIndex));
                }

                tarotCards.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return tarotCards;
    }




}
